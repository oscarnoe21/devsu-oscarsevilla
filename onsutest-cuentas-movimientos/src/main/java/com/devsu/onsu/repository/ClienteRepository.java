package com.devsu.onsu.repository;

import org.springframework.stereotype.Repository;

import com.devsu.onsu.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    
}