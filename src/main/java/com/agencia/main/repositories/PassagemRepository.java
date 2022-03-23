package com.agencia.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.main.model.Passagem;


public interface PassagemRepository extends JpaRepository<Passagem, Long> {

	
}

