package com.devsu.onsu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.onsu.dto.PersonaDTO;
import com.devsu.onsu.entity.Persona;
import com.devsu.onsu.mapper.ClienteMapper;
import com.devsu.onsu.service.PersonaService;

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
@RequestMapping("/personas")
@Tag(name = "Personas", description = "API para la gestion de Personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

       
    /**
     * Obtiene todos los Personas
     * @return
     */
    @Operation(summary = "Obtener todos los Personas", description = "Obtiene una lista de todos los Personas")
    @GetMapping("all") 
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        List<PersonaDTO> personas = personaService.getAllPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK); 
    }

    @Operation(summary = "Obtener Persona por ID", description = "Obtiene una Persona por su ID")
    @GetMapping("/{id}") 
    public ResponseEntity<PersonaDTO> getClientById(
        @Parameter(description = "ID de la Persona a obtener", required = true) @Valid@PathVariable String id
        ) {
            PersonaDTO persona = personaService.getPersonaById(id);
        return new ResponseEntity<>(persona, HttpStatus.OK); 
    }

    /**
     * Guarda un Persona nuevo
     * @param PersonaDTO
     * @return
     */
    @Operation(summary = "Agregar una nueva Persona", description = "Agrega una nueva Persona")
    @PostMapping 
    public ResponseEntity<PersonaDTO> addPersona(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva Persona", required = true, 
                         content = @Content(schema = @Schema(implementation = PersonaDTO.class)))    
        @Valid @RequestBody PersonaDTO personaDTO) {
            PersonaDTO persona = personaService.savePersona(ClienteMapper.toPersonaEntity(personaDTO));
        return new ResponseEntity<>(persona, HttpStatus.CREATED); 
    }

    /**
     * Modifica un Persona totalmente
     * @param id
     * @param PersonaDto
     * @return
     */
    @Operation(summary = "Actualizar una Persona", description = "Actualiza una Persona existente")
    @PutMapping()
    public ResponseEntity<PersonaDTO> updatePersona(@Valid @RequestBody PersonaDTO personaDto) {
        
        Persona persona = ClienteMapper.toPersonaEntity(personaDto);
        PersonaDTO personaUpdateDto = personaService.updatePersona(persona);
        return new ResponseEntity<>(personaUpdateDto, HttpStatus.OK); 
    }

    /**
     * Modifica un Persona parcialmente
     * @param id
     * @param updates
     * @return
     */
    @Operation(summary = "Actualizar parcialmente un Persona", description = "Actualiza parcialmente un Persona existente")
    @PatchMapping("/{id}")
    public ResponseEntity<PersonaDTO> updatePartialPersona(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        PersonaDTO persona = personaService.patchPersona(id, updates);
        return new ResponseEntity<>(persona, HttpStatus.OK); 
    }

    /**
     * Elimina un Persona
     * @param id
     * @return
     */
    @Operation(summary = "Eliminar un Persona", description = "Elimina un Persona por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable String id) {
        return new ResponseEntity<>(personaService.deletePersona(id), HttpStatus.OK); 
    }
    
}
