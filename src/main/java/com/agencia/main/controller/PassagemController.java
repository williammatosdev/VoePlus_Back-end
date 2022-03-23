package com.agencia.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.main.model.Passagem;
import com.agencia.main.repositories.PassagemRepository;

@RestController
@RequestMapping("/passagens")
public class PassagemController {
	
	@Autowired
	private PassagemRepository passagemRepository; 
	
	@GetMapping
	public ResponseEntity<List<Passagem>> findAll(){
		List<Passagem> passagens = passagemRepository.findAll();
		if (!passagens.isEmpty()) {
			return new ResponseEntity<List<Passagem>>(passagens, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Passagem>>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passagem> findById(@PathVariable Long id) {
		Optional<Passagem> passagem = passagemRepository.findById(id);
		if (passagem.isPresent()) {
			return new ResponseEntity<Passagem>(passagem.get(), HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Passagem>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "registrar")
	public ResponseEntity<Passagem> create(@RequestBody Passagem passagem){
		try {
			Passagem c = passagemRepository.save(passagem);
			return new ResponseEntity<Passagem>(c, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
