package com.devsu.onsu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.onsu.entity.Cuenta;
import com.devsu.onsu.exception.ResourceNotFoundException;
import com.devsu.onsu.repository.ClienteRepository;
import com.devsu.onsu.repository.CuentaRepository;


//Contiene la logica de accedo a datos de la aplicacion
@Service
public class CuentaService {
    
  

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    

   
    /**
     * Obtiene todos los cuentas
     * @return
     */
    public List<Cuenta> getAllCuentas() {
        return  cuentaRepository.findAll();
    }

    /**
     * Guarda un cuenta
     * @param cuenta
     * @return
     */
    public Cuenta saveCuenta(Cuenta cuenta) {
        clienteRepository.findById(cuenta.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + cuenta.getClientId()));
        return cuentaRepository.save(cuenta);
    }

    /**
     * Obtiene un cuenta por numeroCuenta
     * @param numeroCuenta
     * @return
     */
    public Cuenta getCuentaByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrado con numero de cuenta: " + numeroCuenta));
    }


    public List<Cuenta> getCuentaByCliente(String clienteId) {
        return cuentaRepository.findByCliente(clienteId);
    }

    /**
     * Actualiza un cuenta
     * @param cuenta
     * @return
     */
    public Cuenta updateCuenta(Cuenta cuenta) {
        clienteRepository.findById(cuenta.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + cuenta.getClientId()));
        Cuenta existingCuenta = cuentaRepository.findById(cuenta.getNumeroCuenta()).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con numero de cuenta: " + cuenta.getNumeroCuenta()));
        
        existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
        existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        existingCuenta.setEstado(cuenta.getEstado());
        return cuentaRepository.save(existingCuenta);
    }

    /**
     * Elimina un cuenta
     * @param numeroCuenta
     * @return
     */
    public String deleteCuenta(String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
        return "Cuenta eliminado con numero de Cuenta: " + numeroCuenta;
    }

    /**
     * Actualiza parcialmente un cuenta
     * @param id
     * @param updates
     * @return
     */
    public Cuenta patchCuenta(Map<String, Object> updates) {
        Cuenta cuenta = cuentaRepository.findById(updates.get("numeroCuenta").toString()).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrado con numero de cuenta: " + updates.get("numeroCuenta")));
        if (cuenta == null) {
            return null;
        }
        updates.forEach((k, v) -> {
            switch (k) {
                
                case "tipoCuenta":
                    cuenta.setTipoCuenta((String) v);
                    break;
                case "saldoInicial":
                    cuenta.setSaldoInicial((Double) v);
                    break;
                case "estado":
                    cuenta.setEstado((String) v);
                    break;
                case "clientId":
                    clienteRepository.findById((String) v).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + v));
                    cuenta.setClientId((String) v);
                    break;
                default:
                    break;
            }
        });
        return cuentaRepository.save(cuenta);
       
    }


}
