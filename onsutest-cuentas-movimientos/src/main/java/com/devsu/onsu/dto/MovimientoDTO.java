package com.devsu.onsu.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {

    @NotNull(message = "La fecha es obligatoria")
    private Date fecha;

    @NotBlank(message = "El tipoMovimiento es obligatorio")
    @NotNull(message = "El tipoMovimiento es obligatorio")
    private String tipoMovimiento;

    
    @NotNull(message = "El valor es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener máximo 10 enteros y 2 decimales")    
    private Double valor;

    
    @NotNull(message = "El saldo es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El saldo debe tener máximo 10 enteros y 2 decimales")    
    private Double saldo;

    @NotBlank(message = "El numeroCuenta es obligatorio")
    @NotNull(message = "El numeroCuenta es obligatorio")
    private String numeroCuenta;
}
