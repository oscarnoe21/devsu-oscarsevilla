package com.devsu.onsu.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {



    @NotBlank(message = "El clientId es obligatorio")
    @NotNull(message = "El clientId es obligatorio")
    private String clientId;

    @NotBlank(message = "La contrasena es obligatoria")
    @NotNull(message = "La contrasena es obligatoria")
    private String contrasena;

    @NotBlank(message = "El estado es obligatorio")
    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "La identificacion es obligatoria")
    @NotNull(message = "La identificacion es obligatoria")
    private String identificacion;

    
}
