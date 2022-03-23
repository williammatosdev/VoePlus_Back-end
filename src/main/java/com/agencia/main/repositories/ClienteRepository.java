package com.agencia.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.main.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
}

