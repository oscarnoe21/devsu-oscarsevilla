package com.devsu.onsu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.onsu.dto.PersonaDTO;
import com.devsu.onsu.entity.Persona;
import com.devsu.onsu.exception.ResourceNotFoundException;
import com.devsu.onsu.mapper.ClienteMapper;
import com.devsu.onsu.repository.PersonaRepository;



@Service
public class PersonaService {
    
  

    @Autowired
    private PersonaRepository personaRepository;

    

   
    /**
     * Obtiene todos las personas
     * @return
     */
    public List<PersonaDTO> getAllPersonas() {
        return  ClienteMapper.toPersonaDtoList(personaRepository.findAll());
    }

    /**
     * Guarda una persona
     * @param persona
     * @return
     */
    public PersonaDTO savePersona(Persona persona) {
        return ClienteMapper.toPesonaDto(personaRepository.save(persona));
    }

    /**
     * Obtiene un persona por id
     * @param id
     * @return
     */
    public PersonaDTO getPersonaById(String id) {
        return ClienteMapper.toPesonaDto(personaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Persona no encontrado con id: " + id)));
    }

    /**
     * Actualiza un persona
     * @param persona
     * @return
     */
    public PersonaDTO updatePersona(Persona persona) {
        Persona existingPersona = personaRepository.findById(persona.getIdentificacion()).orElseThrow(() -> new ResourceNotFoundException("Persona no encontrado con id: " + persona.getIdentificacion()));
        existingPersona.setDireccion(persona.getDireccion());
        existingPersona.setEdad(persona.getEdad());
        existingPersona.setGenero(persona.getGenero());
        existingPersona.setIdentificacion(persona.getIdentificacion());
        existingPersona.setNombre(persona.getNombre());
        existingPersona.setTelefono(persona.getTelefono());
        return ClienteMapper.toPesonaDto(personaRepository.save(existingPersona));
    }

    /**
     * Elimina una persona
     * @param id
     * @return
     */
    public String deletePersona(String id) {
        personaRepository.deleteById(id);
        return "Persona eliminada con id: " + id;
    }

    /**
     * Actualiza parcialmente un persona
     * @param id
     * @param updates
     * @return
     */
    public PersonaDTO patchPersona( String id,  Map<String, Object> updates) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Persona no encontrado con id: " + id));
        if (persona == null) {
            return null;
        }
        updates.forEach((k, v) -> {
            switch (k) {
                
                case "nombre":
                    persona.setNombre((String) v);
                    break;
                case "genero":
                    persona.setGenero((String) v);
                    break;
                case "edad":
                    persona.setEdad((Integer) v);
                    break;
                //case "identificacion":
                  //  persona.setIdentificacion((String) v);
                    //break;  
                case "direccion":
                    persona.setDireccion((String) v);
                    break;
                case "telefono":
                    persona.setTelefono((String) v);
                    break;
                default:
                    break;
            }
        });
        return ClienteMapper.toPesonaDto(personaRepository.save(persona));
       
    }


}
