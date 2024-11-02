package com.devsu.onsu.dto.reporte;

import java.util.List;

import com.devsu.onsu.dto.CuentaDTO;
import com.devsu.onsu.dto.MovimientoDTO;


public class CuentaReporteDTO extends CuentaDTO {
    private List<MovimientoDTO> movimientos;

    public List<MovimientoDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDTO> movimientos) {
        this.movimientos = movimientos;
    }
    
}
