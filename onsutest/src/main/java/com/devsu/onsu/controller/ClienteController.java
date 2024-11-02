package com.devsu.onsu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.onsu.dto.ClienteDTO;
import com.devsu.onsu.entity.Cliente;
import com.devsu.onsu.mapper.ClienteMapper;
import com.devsu.onsu.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para la gestion de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

       
    /**
     * Obtiene todos los clientes
     * @return
     */
    @Operation(summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes")
    @GetMapping("all") 
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK); 
    }

    @Operation(summary = "Obtener cliente por ID", description = "Obtiene un cliente por su ID")
    @GetMapping("/{id}") 
    public ResponseEntity<ClienteDTO> getClienteById(
        @Parameter(description = "ID del cliente a obtener", required = true) @Valid@PathVariable String id
        ) {
            ClienteDTO cliente = clienteService.getClienteById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK); 
    }

    /**
     * Guarda un cliente nuevo
     * @param clienteDTO
     * @return
     */
    @Operation(summary = "Agregar un nuevo cliente", description = "Agrega un nuevo cliente")
    @PostMapping 
    public ResponseEntity<Cliente> addCliente(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo cliente", required = true, 
                         content = @Content(schema = @Schema(implementation = ClienteDTO.class)))    
        @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.saveCliente(ClienteMapper.toClienteEntity(clienteDTO));
        return new ResponseEntity<>(cliente, HttpStatus.CREATED); 
    }

    /**
     * Modifica un cliente totalmente
     * @param id
     * @param clienteDto
     * @return
     */
    @Operation(summary = "Actualizar un cliente", description = "Actualiza un cliente existente")
    @PutMapping()
    public ResponseEntity<ClienteDTO> updateCliente(@Valid @RequestBody ClienteDTO clienteDto) {
        
        Cliente cliente = ClienteMapper.toClienteEntity(clienteDto);
        ClienteDTO clienteDtoUpdate = clienteService.updateCliente(cliente);
        return new ResponseEntity<>(clienteDtoUpdate, HttpStatus.OK); 
    }

    /**
     * Modifica un cliente parcialmente
     * @param id
     * @param updates
     * @return
     */
    @Operation(summary = "Actualizar parcialmente un cliente", description = "Actualiza parcialmente un cliente existente")
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDTO> updatePartialCliente(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        ClienteDTO cliente = clienteService.patchCliente(id, updates);
        return new ResponseEntity<>(cliente, HttpStatus.OK); 
    }

    /**
     * Elimina un cliente
     * @param id
     * @return
     */
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable String id) {
        return new ResponseEntity<>(clienteService.deleteCliente(id), HttpStatus.OK); 
    }
    
}
