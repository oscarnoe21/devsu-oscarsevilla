package com.devsu.onsu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.onsu.dto.reporte.ClienteReporteDTO;
import com.devsu.onsu.dto.reporte.CuentaReporteDTO;
import com.devsu.onsu.entity.Cuenta;
import com.devsu.onsu.entity.Movimientos;
import com.devsu.onsu.exception.ResourceNotFoundException;
import com.devsu.onsu.mapper.ClassMapper;
import com.devsu.onsu.repository.MovimientoRepository;

@Service
public class MovimientosService {
    private static final String MOVIMIENTO_NO_ENCONTRADO_CON_ID = "Movimiento no encontrado con id: ";

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    /**
     * Obtiene todos los movimientos
     * 
     * @return
     */
    public List<Movimientos> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    /**
     * Guarda un movimiento
     * 
     * @param movimiento
     * @return
     */
    public Movimientos saveMovimiento(Movimientos movimiento) {

        Cuenta cuenta = cuentaService.getCuentaByNumeroCuenta(movimiento.getNumeroCuenta());
        // F3
        // Se obtiene el ultimo movimiento para obtener el saldo de la cuenta
        List<Movimientos> ultimoMovimiento = movimientoRepository
                .findUltimoMovimientosByCuenta(movimiento.getNumeroCuenta());

        // Se busca el saldo actual de la cuenta
        Double saldo;
        if (!ultimoMovimiento.isEmpty()) {
            saldo = ultimoMovimiento.get(0).getSaldo();
        } else {
            saldo = cuenta.getSaldoInicial();
        }

        //F3
        // Si el valor del movimiento es negativo y el saldo de la cuenta es menor al valor absoluto del movimiento
        // se muestra el mensaje de error
        if (movimiento.getValor() < 0 && saldo < (movimiento.getValor()*-1)) {
            throw new ResourceNotFoundException("Saldo insuficiente para realizar la transaccion");
        }

        // se calcula el nuevo saldo
        saldo += movimiento.getValor();

        // F2
        // Se guarda el movimiento con el saldo final de la cuenta despues del
        // movimiento
        movimiento.setSaldo(saldo);

        // Se registra el movimiento
        return movimientoRepository.save(movimiento);
    }

    /**
     * Obtiene un movimiento por id
     * 
     * @param id
     * @return
     */
    public Movimientos getMovimientoById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MOVIMIENTO_NO_ENCONTRADO_CON_ID + id));
    }

    /**
     * Actualiza un movimiento
     * 
     * @param movimiento
     * @return
     */
    public Movimientos updateMovimiento(Movimientos movimiento) {
        cuentaService.getCuentaByNumeroCuenta(movimiento.getNumeroCuenta());

        Movimientos existingMovimiento = movimientoRepository.findById(movimiento.getId())
                .orElseThrow(() -> new ResourceNotFoundException(MOVIMIENTO_NO_ENCONTRADO_CON_ID + movimiento.getId()));
        existingMovimiento.setFecha(movimiento.getFecha());
        existingMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
        existingMovimiento.setSaldo(movimiento.getSaldo());
        existingMovimiento.setValor(movimiento.getValor());
        return movimientoRepository.save(existingMovimiento);
    }

    /**
     * Elimina un movimiento
     * 
     * @param id
     * @return
     */
    public String deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
        return "Movimiento eliminado con id: " + id;
    }

    /**
     * Actualiza parcialmente un movimiento
     * 
     * @param id
     * @param updates
     * @return
     */
    public Movimientos patchMovimiento(Long id, Map<String, Object> updates) {
        Movimientos movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MOVIMIENTO_NO_ENCONTRADO_CON_ID + id));
        if (updates.containsKey("numeroCuenta")) {
            cuentaService.getCuentaByNumeroCuenta(updates.get("numeroCuenta").toString());
        }
        if (movimiento == null) {
            return null;
        }
        updates.forEach((k, v) -> {
            switch (k) {
                /*
                 * case "fecha":
                 * Date.parse(v.toString());
                 * movimiento.setFecha((Date) v);
                 * break;
                 */
                case "tipoMovimiento":
                    movimiento.setTipoMovimiento((String) v);
                    break;
                case "valor":
                    movimiento.setValor((Double) v);
                    break;
                case "saldo":
                    movimiento.setSaldo((Double) v);
                    break;
                case "numeroCuenta": {
                    movimiento.setNumeroCuenta((String) v);
                    break;
                }
                default:
                    break;
            }
        });
        return movimientoRepository.save(movimiento);
    }

    // F4: Reporte de movimientos
    /**
     * Genera un reporte de movimientos
     * @param clienteId
     */
    public ClienteReporteDTO reporteMovimientos(String clienteId, Date fechaInicio, Date fechaFin) {
        ClienteReporteDTO clienteReporte = new ClienteReporteDTO();

        //Se recuperan los datos del cliente
        List<Object[]> clientInfo = movimientoRepository.findDataCliente(clienteId);
        if (clientInfo.isEmpty()) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + clienteId);
        }
        clienteReporte.setClienteId(clientInfo.get(0)[0].toString());
        clienteReporte.setIdentificacion(clientInfo.get(0)[1].toString());
        clienteReporte.setNombre(clientInfo.get(0)[2].toString());
        
        //Se recuperan las cuentas del cliente
        List<Cuenta> cuentasCliente = cuentaService.getCuentaByCliente(clienteId);
        List<CuentaReporteDTO> cuentasReporte = ClassMapper.toListCuentaReporteDto(cuentasCliente);
        clienteReporte.setCuentas(cuentasReporte);

        //Se recuperan los momientos de las cuentas
        for (CuentaReporteDTO cuentaReporte : clienteReporte.getCuentas()  ) {
            List<Movimientos> movimientos = movimientoRepository.findMovimientosByCuenta(cuentaReporte.getNumeroCuenta(), fechaInicio, fechaFin);
            cuentaReporte.setMovimientos(ClassMapper.toListDtoMovimiento(movimientos));
        }
        return clienteReporte;
    }
}
