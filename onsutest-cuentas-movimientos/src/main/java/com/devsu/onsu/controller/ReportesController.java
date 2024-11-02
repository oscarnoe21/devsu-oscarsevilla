package com.devsu.onsu.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.onsu.dto.reporte.ClienteReporteDTO;
import com.devsu.onsu.service.MovimientosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reportes")
@Tag(name = "Reportes", description = "API de reporteria")
public class ReportesController {

    @Autowired
    private MovimientosService movimientoService;

    /**
     * Obtiene reporte de transacciones
     * @return
     */
    @Operation(summary = "Muestra el reporte del cliente", description = "Muestra las cuentas y transacciones del cliente")
    @GetMapping("/{idCliente}") 
    public ResponseEntity<ClienteReporteDTO> getReporte(
        @Parameter(description = "Id del cliente", required = true) @Valid @PathVariable String idCliente,
        @Parameter(description = "Fecha de inicio del rango", required = true) @Valid @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
        @Parameter(description = "Fecha de fin del rango", required = true) @Valid @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {

        ClienteReporteDTO reporte = movimientoService.reporteMovimientos(idCliente, fechaInicio, fechaFin);
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }




}
