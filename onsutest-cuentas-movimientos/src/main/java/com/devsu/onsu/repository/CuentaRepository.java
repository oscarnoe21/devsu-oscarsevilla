package com.devsu.onsu.repository;

import org.springframework.stereotype.Repository;

import com.devsu.onsu.entity.Cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    @Query("select c from Cuenta c where c.clientId = :cliente_id")
    List<Cuenta> findByCliente(@Param("cliente_id") String clientId);

    
}