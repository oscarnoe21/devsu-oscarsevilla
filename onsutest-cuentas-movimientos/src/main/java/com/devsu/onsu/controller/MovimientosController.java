package com.devsu.onsu.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.onsu.dto.MovimientoDTO;
import com.devsu.onsu.entity.Movimientos;
import com.devsu.onsu.mapper.ClassMapper;
import com.devsu.onsu.service.MovimientosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movimientos")
@Tag(name = "Movimientos", description = "API para la gestion de Movimientos")
public class MovimientosController {

    @Autowired
    private MovimientosService movimientoService;

    
       
    /**
     * Obtiene todos los movimientos
     * @return
     */
    @Operation(summary = "Obtener todos los movimientos", description = "Obtiene una lista de todos los movimientos")
    @GetMapping("all") 
    public ResponseEntity<List<Movimientos>> getAllMovimientos() {
        List<Movimientos> movimientos = movimientoService.getAllMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK); 
    }

    @Operation(summary = "Obtener movimiento por ID", description = "Obtiene un movimiento por su ID")
    @GetMapping("/{id}") 
    public ResponseEntity<Movimientos> getClientById(
        @Parameter(description = "ID del movimiento a obtener", required = true) @Valid@PathVariable Long id
        ) {
        Movimientos movimiento = movimientoService.getMovimientoById(id);
        return new ResponseEntity<>(movimiento, HttpStatus.OK); 
    }

    /**
     * Guarda un movimiento nuevo
     * @param movimientoDTO
     * @return
     */
    @Operation(summary = "Agregar un nuevo movimiento", description = "Agrega un nuevo movimiento")
    @PostMapping 
    public ResponseEntity<Movimientos> addMovimiento(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo movimiento", required = true, 
                         content = @Content(schema = @Schema(implementation = MovimientoDTO.class)))    
        @Valid @RequestBody MovimientoDTO movimientoDTO) {
        Movimientos movimiento = movimientoService.saveMovimiento(ClassMapper.toEntityMovimiento(movimientoDTO));
        return new ResponseEntity<>(movimiento, HttpStatus.CREATED); 
    }

    /**
     * Modifica un movimiento totalmente
     * @param id
     * @param movimientoDto
     * @return
     */
    @Operation(summary = "Actualizar un movimiento", description = "Actualiza un movimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<Movimientos> updateMovimiento(@PathVariable Long id,@Valid @RequestBody MovimientoDTO movimientoDto) {
        
        Movimientos movimiento = ClassMapper.toEntityMovimiento(movimientoDto);
        movimiento.setId(id);
        movimiento = movimientoService.updateMovimiento(movimiento);
        return new ResponseEntity<>(movimiento, HttpStatus.OK); 
    }

    /**
     * Modifica un movimiento parcialmente
     * @param id
     * @param updates
     * @return
     */
    @Operation(summary = "Actualizar parcialmente un movimiento", description = "Actualiza parcialmente un movimiento existente")
    @PatchMapping("/{id}")
    public ResponseEntity<Movimientos> updatePartialMovimiento(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Movimientos movimiento = movimientoService.patchMovimiento(id, updates);
        return new ResponseEntity<>(movimiento, HttpStatus.OK); 
    }

    /**
     * Elimina un movimiento
     * @param id
     * @return
     */
    @Operation(summary = "Eliminar un movimiento", description = "Elimina un movimiento por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovimiento(@PathVariable Long id) {
        return new ResponseEntity<>(movimientoService.deleteMovimiento(id), HttpStatus.OK); 
    }

}
