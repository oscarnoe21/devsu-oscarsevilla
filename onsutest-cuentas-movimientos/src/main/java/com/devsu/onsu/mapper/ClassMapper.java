package com.devsu.onsu.mapper;

import java.util.ArrayList;
import java.util.List;

import com.devsu.onsu.dto.CuentaDTO;
import com.devsu.onsu.dto.MovimientoDTO;
import com.devsu.onsu.dto.reporte.CuentaReporteDTO;
import com.devsu.onsu.entity.Cuenta;
import com.devsu.onsu.entity.Movimientos;

/**
 * Conversion de Dtos a Entidades y viceversa
 */
public class ClassMapper {

    private ClassMapper() {
    }

    public static Cuenta toEntityCuenta(CuentaDTO cuentaDTO) {
        
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());
        cuenta.setClientId(cuentaDTO.getClientId());
        return cuenta;
    }

    public static CuentaDTO toDtoCuenta(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.getEstado());
        cuentaDTO.setClientId(cuenta.getClientId());
        return cuentaDTO;
    }

    public static Movimientos toEntityMovimiento(MovimientoDTO movimientoDTO) {
        
        Movimientos movimiento = new Movimientos();
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setSaldo(movimientoDTO.getSaldo());
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setNumeroCuenta(movimientoDTO.getNumeroCuenta());

        return movimiento;
    }

    public static MovimientoDTO toDtoMovimiento(Movimientos movimiento) {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setFecha(movimiento.getFecha());
        movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDTO.setSaldo(movimiento.getSaldo());
        movimientoDTO.setValor(movimiento.getValor());
        movimientoDTO.setNumeroCuenta(movimiento.getNumeroCuenta());
        return movimientoDTO;
    }

    public static List<MovimientoDTO> toListDtoMovimiento(List<Movimientos> movimientos) {
        List<MovimientoDTO> movimientosDTO = new ArrayList<>();
        for (Movimientos movimiento : movimientos) {
            movimientosDTO.add(toDtoMovimiento(movimiento));
        }
        return movimientosDTO;
    }

    public static List<CuentaDTO> toListDtoCuenta(List<Cuenta> cuentas) {
        List<CuentaDTO> cuentasDTO = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            cuentasDTO.add(toDtoCuenta(cuenta));
        }
        return cuentasDTO;
    }

    public static List<CuentaReporteDTO> toListCuentaReporteDto(List<Cuenta> cuentas)
    {
        List<CuentaReporteDTO> cuentasReporteDTO = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            CuentaReporteDTO cuentaReporteDTO = new CuentaReporteDTO();
            cuentaReporteDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaReporteDTO.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaReporteDTO.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaReporteDTO.setEstado(cuenta.getEstado());
            cuentaReporteDTO.setClientId(cuenta.getClientId());
            cuentasReporteDTO.add(cuentaReporteDTO);
        }
        return cuentasReporteDTO;
    }
}
