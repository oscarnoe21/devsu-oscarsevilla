package com.devsu.onsu.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class Persona {
    @Id
    private String identificacion;
    private String nombre;
    private String genero;
    private int edad;   
    private String direccion;
    private String telefono;

    //@OneToMany
   // List<Cliente> clientes;
}
