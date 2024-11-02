package com.devsu.onsu.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {

    @NotBlank(message = "El numeroCuenta es obligatorio")
    @NotNull(message = "El numeroCuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipoCuenta es obligatorio")
    @NotNull(message = "El tipoCuenta es obligatorio")
    private String tipoCuenta;
   

    @NotNull(message = "El saldoInicial es obligatorio")
    @PositiveOrZero(message = "El saldoInicial debe ser mayor o igual a cero")
    private Double saldoInicial;

    @NotBlank(message = "El estado es obligatorio")
    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El estado es obligatorio")
    @NotNull(message = "El estado es obligatorio")
    private String clientId;

    
}
