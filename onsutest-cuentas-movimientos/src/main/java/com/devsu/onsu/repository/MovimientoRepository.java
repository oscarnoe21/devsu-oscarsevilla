package com.devsu.onsu.repository;


import com.devsu.onsu.entity.Movimientos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos, Long> {

    @Query("select m from Movimientos m where m.numeroCuenta = :numero_cuenta order by id desc limit 1")
    List<Movimientos> findUltimoMovimientosByCuenta(@Param("numero_cuenta") String numeroCuenta);

    @Query("select m from Movimientos m where m.numeroCuenta = :numero_cuenta  and m.fecha between :fechaInicio and :fechaFin order by id desc")
    List<Movimientos> findMovimientosByCuenta(@Param("numero_cuenta") String numeroCuenta, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query(value ="select c.client_id, p.identificacion, p.nombre from cliente c inner join persona p on p.identificacion  = c.identificacion where c.client_id = :cliente_id", nativeQuery = true)
    List<Object[]> findDataCliente(@Param("cliente_id") String clienteId);

    


}
