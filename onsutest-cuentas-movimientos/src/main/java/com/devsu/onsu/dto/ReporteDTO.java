package com.devsu.onsu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    private String idTransaction;
    private String fecha;
    private String nombre;
    private String numeroCuenta;
    private String tipoMovimiento;
    private String saldoInicial;
    private String estado;
    private String valor;
    private String saldo;
}
