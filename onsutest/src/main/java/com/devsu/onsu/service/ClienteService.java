package com.devsu.onsu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.onsu.dto.ClienteDTO;
import com.devsu.onsu.entity.Cliente;
import com.devsu.onsu.exception.ResourceNotFoundException;
import com.devsu.onsu.mapper.ClienteMapper;
import com.devsu.onsu.repository.ClienteRepository;

//Contiene la logica de accedo a datos de la aplicacion
@Service
public class ClienteService {

    private static final String CLIENTE_NO_ENCONTRADO_CON_ID = "Cliente no encontrado con id: ";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaService personaService;

    /**
     * Obtiene todos los clientes
     * 
     * @return
     */
    public List<ClienteDTO> getAllClientes() {
        return ClienteMapper.toClienteDtoList(clienteRepository.findAll());
    }

    /**
     * Guarda un cliente
     * 
     * @param cliente
     * @return
     */
    public Cliente saveCliente(Cliente cliente) {
        // Valida si existe la persona de la identificacion
        personaService.getPersonaById(cliente.getIdentificacion());
        return clienteRepository.save(cliente);
    }

    /**
     * Obtiene un cliente por id
     * 
     * @param id
     * @return
     */
    public ClienteDTO getClienteById(String id) {
        return ClienteMapper.toClienteDto(clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NO_ENCONTRADO_CON_ID + id)));
    }

    /**
     * Actualiza un cliente
     * 
     * @param cliente
     * @return
     */
    public ClienteDTO updateCliente(Cliente cliente) {
        // Valida si existe la persona de la identificacion
        personaService.getPersonaById(cliente.getIdentificacion());
        Cliente existingCliente = clienteRepository.findById(cliente.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NO_ENCONTRADO_CON_ID + cliente.getClientId()));
        existingCliente.setClientId(cliente.getClientId());
        existingCliente.setContrasena(cliente.getContrasena());
        existingCliente.setEstado(cliente.getEstado());
        return ClienteMapper.toClienteDto(clienteRepository.save(existingCliente));
    }

    /**
     * Elimina un cliente
     * 
     * @param id
     * @return
     */
    public String deleteCliente(String id) {
        clienteRepository.deleteById(id);
        return "Cliente eliminado con id: " + id;
    }

    /**
     * Actualiza parcialmente un cliente
     * 
     * @param id
     * @param updates
     * @return
     */
    public ClienteDTO patchCliente(String id, Map<String, Object> updates) {
        
        if(updates.containsKey("identificacion")) {
            //valida si existe la persona de la identificacion
            personaService.getPersonaById((String) updates.get("identificacion"));
        }
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NO_ENCONTRADO_CON_ID + id));
        if (cliente == null) {
            return null;
        }
        updates.forEach((k, v) -> {
            switch (k) {
                case "clientId":
                    cliente.setClientId((String) v);
                    break;
                case "contrasena":
                    cliente.setContrasena((String) v);
                    break;
                case "estado":
                    cliente.setEstado((String) v);
                    break;

                default:
                    break;
            }
        });
        return ClienteMapper.toClienteDto(clienteRepository.save(cliente));

    }

}
