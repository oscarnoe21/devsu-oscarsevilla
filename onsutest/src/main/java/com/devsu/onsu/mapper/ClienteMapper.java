package com.devsu.onsu.mapper;

import java.util.List;

import com.devsu.onsu.dto.ClienteDTO;
import com.devsu.onsu.dto.PersonaDTO;
import com.devsu.onsu.entity.Cliente;
import com.devsu.onsu.entity.Persona;


public class ClienteMapper {

    private ClienteMapper() {
    }

    public static Persona toPersonaEntity(PersonaDTO personaDTO)
    {
        Persona persona = new Persona();
        persona.setNombre(personaDTO.getNombre());
        persona.setGenero(personaDTO.getGenero());
        persona.setEdad(personaDTO.getEdad());
        persona.setIdentificacion(personaDTO.getIdentificacion());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setTelefono(personaDTO.getTelefono());
        return persona;
    }

    public static PersonaDTO toPesonaDto(Persona persona)
    {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setGenero(persona.getGenero());
        personaDTO.setEdad(persona.getEdad());
        personaDTO.setIdentificacion(persona.getIdentificacion());
        personaDTO.setDireccion(persona.getDireccion());
        personaDTO.setTelefono(persona.getTelefono());
        return personaDTO;
    }

    public static Cliente toClienteEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        
        cliente.setClientId(clienteDTO.getClientId());
        cliente.setContrasena(clienteDTO.getContrasena());
        cliente.setEstado(clienteDTO.getEstado());
        cliente.setIdentificacion(clienteDTO.getIdentificacion());

        return cliente;
    }

    public static ClienteDTO toClienteDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        
        clienteDTO.setClientId(cliente.getClientId());
        clienteDTO.setContrasena(cliente.getContrasena());
        clienteDTO.setEstado(cliente.getEstado());
        clienteDTO.setIdentificacion(cliente.getIdentificacion());

        return clienteDTO;
    }

    public static List<ClienteDTO> toClienteDtoList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapper::toClienteDto).toList();
    }

    public static List<PersonaDTO> toPersonaDtoList(List<Persona> personas) {
        return personas.stream().map(ClienteMapper::toPesonaDto).toList();
    }
}
