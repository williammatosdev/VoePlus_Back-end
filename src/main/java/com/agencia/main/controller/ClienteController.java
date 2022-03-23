package com.agencia.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.main.model.Cliente;
import com.agencia.main.repositories.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = clienteRepository.findAll();
		if (!clientes.isEmpty()) {
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Cliente>>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "registrar")
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
		try {
			Cliente c = clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(c, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "att")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
		Optional<Cliente> c = clienteRepository.findById(id);
		if (c.isPresent()) {
			Cliente c2 = c.get();
			c2.setName(cliente.getName());
			c2.setEmail(cliente.getEmail());
			return new ResponseEntity<Cliente>(c2, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
