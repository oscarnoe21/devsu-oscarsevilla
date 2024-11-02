package com.devsu.onsu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.onsu.dto.CuentaDTO;
import com.devsu.onsu.entity.Cuenta;
import com.devsu.onsu.mapper.ClassMapper;
import com.devsu.onsu.service.CuentaService;

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
@RequestMapping("/cuentas")
@Tag(name = "Cuentas", description = "API para la gestion de cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

       
    /**
     * Obtiene todas las cuentas
     * @return
     */
    @Operation(summary = "Obtener todas las cuentas", description = "Obtiene una lista de todas las cuentas")
    @GetMapping("all") 
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.getAllCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK); 
    }

    @Operation(summary = "Obtener cuenta por numeroCuenta", description = "Obtiene un cuenta por su numeroCuenta")
    @GetMapping("/{numeroCuenta}") 
    public ResponseEntity<Cuenta> getClientById(
        @Parameter(description = "Numero Cuenta del cuenta a obtener", required = true) @Valid@PathVariable String numeroCuenta
        ) {
        Cuenta cuenta = cuentaService.getCuentaByNumeroCuenta(numeroCuenta);
        return new ResponseEntity<>(cuenta, HttpStatus.OK); 
    }

    /**
     * Guarda un cuenta nueva
     * @param cuentaDTO
     * @return
     */
    @Operation(summary = "Agregar una nueva cuenta", description = "Agrega una nueva cuenta")
    @PostMapping 
    public ResponseEntity<Cuenta> addCuenta(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva cuenta", required = true, 
                         content = @Content(schema = @Schema(implementation = CuentaDTO.class)))    
        @Valid @RequestBody CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaService.saveCuenta(ClassMapper.toEntityCuenta(cuentaDTO));
        return new ResponseEntity<>(cuenta, HttpStatus.CREATED); 
    }

    /**
     * Modifica un cuenta totalmente
     * @param id
     * @param cuentaDto
     * @return
     */
    @Operation(summary = "Actualizar una cuenta", description = "Actualiza una cuenta existente")
    @PutMapping()
    public ResponseEntity<Cuenta> updateCuenta(@Valid @RequestBody CuentaDTO cuentaDto) {
        
        Cuenta cuenta = ClassMapper.toEntityCuenta(cuentaDto);
        cuenta = cuentaService.updateCuenta(cuenta);
        return new ResponseEntity<>(cuenta, HttpStatus.OK); 
    }

    /**
     * Modifica un cuenta parcialmente
     * @param id
     * @param updates
     * @return
     */
    @Operation(summary = "Actualizar parcialmente una cuenta", description = "Actualiza parcialmente una cuenta existente")
    @PatchMapping()
    public ResponseEntity<Cuenta> updatePartialCuenta(@RequestBody Map<String, Object> updates) {
        Cuenta cuenta = cuentaService.patchCuenta(updates);
        return new ResponseEntity<>(cuenta, HttpStatus.OK); 
    }

    /**
     * Elimina un cuenta
     * @param id
     * @return
     */
    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta por su numero de Cuenta")
    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<String> deleteCuenta(@PathVariable String numeroCuenta) {
        return new ResponseEntity<>(cuentaService.deleteCuenta(numeroCuenta), HttpStatus.OK); 
    }
    
}
