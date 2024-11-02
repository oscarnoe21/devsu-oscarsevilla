package com.devsu.onsu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.devsu.onsu.dto.ClienteDTO;
import com.devsu.onsu.entity.Cliente;
import com.devsu.onsu.exception.ResourceNotFoundException;
import com.devsu.onsu.mapper.ClienteMapper;
import com.devsu.onsu.service.ClienteService;


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClientes() {
        ClienteDTO cliente1 = new ClienteDTO("1", "123pass", "activo", "1234567890");
        ClienteDTO cliente2 = new ClienteDTO("1", "pass123", "inactivo", "0987654321");
        List<ClienteDTO> clientesSimulados = Arrays.asList(cliente1, cliente2);

        when(clienteService.getAllClientes()).thenReturn(clientesSimulados);

        ResponseEntity<List<ClienteDTO>> response = clienteController.getAllClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientesSimulados, response.getBody());
    }

    @Test
    void testGetClientById() {
        ClienteDTO cliente = new ClienteDTO("1", "123pass", "activo", "1234567890");

        when(clienteService.getClienteById("1")).thenReturn(cliente);

        ResponseEntity<ClienteDTO> response = clienteController.getClienteById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testGetClienteByIdNotFound() {
        when(clienteService.getClienteById("1"))
                .thenThrow(new ResourceNotFoundException("Cliente no encontrado con id: 1"));

        try {
            clienteController.getClienteById("1");
        } catch (Exception e) {
            assertInstanceOf(ResourceNotFoundException.class, e);
            assertEquals("Cliente no encontrado con id: 1", e.getMessage());

        }

    }

    @Test
    void testAddCliente() {
        
        ClienteDTO clienteDTO = new ClienteDTO("1", "123pass", "activo", "1234567890");  
        Cliente cliente = ClienteMapper.toClienteEntity(clienteDTO);
        when(clienteService.saveCliente(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.addCliente(clienteDTO);
        Cliente clienteResponse = response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, clienteResponse);
    }

    @Test
    void testUpdateCliente() {
        ClienteDTO clienteDTO = new ClienteDTO("1", "123pass", "activo", "1234567890");

        Cliente cliente = ClienteMapper.toClienteEntity(clienteDTO);
        when(clienteService.updateCliente(cliente)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = clienteController.updateCliente(clienteDTO);
        ClienteDTO clienteResponse = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, clienteResponse);
    }

    @Test
    void testUpdatePartialCliente() {
        ClienteDTO clienteDTO = new ClienteDTO ("1", "123pass", "activo", "1234567890");
        Map<String, Object> updates = Map.of( "estado", "activo", "clientId", "1", "contrasena", "123pass", "identificacion", "1234567890");
        
        
        when(clienteService.patchCliente("1", updates)).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = clienteController.updatePartialCliente("1", updates);
        ClienteDTO clienteResponse = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, clienteResponse);
    }

    @Test
    void testDeleteCliente() {
        when(clienteService.deleteCliente("1")).thenReturn("Cliente eliminado con id: 1");

        ResponseEntity<String> response = clienteController.deleteCliente("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente eliminado con id: 1", response.getBody());
    }

}