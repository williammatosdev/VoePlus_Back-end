package com.agencia.main.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agencia.main.model.Cliente;
import com.agencia.main.model.Passagem;
import com.agencia.main.repositories.ClienteRepository;
import com.agencia.main.repositories.PassagemRepository;

@Configuration
@Profile("test")
public class TesteConfig implements ApplicationRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PassagemRepository ps;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Cliente cliente1 = new Cliente("joao", "joao@gmail.com", "123456789");
		Cliente cliente2 = new Cliente("marcos", "marcos@gmail.com", "45678891");
		
		Passagem p1 = new Passagem("MG", "RJ", 2000.00, cliente1);
		Passagem p2 = new Passagem("SP", "RJ", 1000.95, cliente2);
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		ps.saveAll(Arrays.asList(p1, p2));
		
	}
}
