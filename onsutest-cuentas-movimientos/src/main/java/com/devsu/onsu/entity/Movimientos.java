package com.devsu.onsu.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movimientos")
public class Movimientos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;

    //@ManyToOne(targetEntity = Cuenta.class)
    //@JoinColumn(name = "numeroCuenta", referencedColumnName = "numeroCuenta")  
    private String numeroCuenta;


}
