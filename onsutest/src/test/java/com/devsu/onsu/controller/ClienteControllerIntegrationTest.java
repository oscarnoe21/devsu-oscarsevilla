package com.devsu.onsu.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devsu.onsu.dto.ClienteDTO;
import com.devsu.onsu.entity.Cliente;
import com.devsu.onsu.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testGetClienteById() throws Exception {
        // Crear una persona simulada
        ClienteDTO cliente = new ClienteDTO("1", "123pass", "activo", "1234567890");

        // Configurar el comportamiento del servicio simulado
        when(clienteService.getClienteById("1")).thenReturn(cliente);

        // Llamar al método del controlador y verificar el resultado
        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value("1"))
                .andExpect(jsonPath("$.contrasena").value("123pass"))
                .andExpect(jsonPath("$.estado").value("activo"))
                .andExpect(jsonPath("$.identificacion").value("1234567890"));
                
    }
}
