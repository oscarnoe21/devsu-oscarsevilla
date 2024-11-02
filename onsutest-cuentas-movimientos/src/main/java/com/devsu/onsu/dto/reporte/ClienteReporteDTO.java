package com.devsu.onsu.dto.reporte;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteReporteDTO {
    private String identificacion;
    private String clienteId;
    private String nombre;

    private List<CuentaReporteDTO> cuentas;
}
