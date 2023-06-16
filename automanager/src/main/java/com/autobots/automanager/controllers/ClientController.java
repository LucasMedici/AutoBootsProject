package com.autobots.automanager.controllers;

import java.util.List;

import com.autobots.automanager.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.models.ClientUpdate;
import com.autobots.automanager.models.ClientSelect;
import com.autobots.automanager.repositories.ClientRepository;

@RestController
@RequestMapping("/cliente")
public class ClientController {
	@Autowired
	private ClientRepository repository;
	@Autowired
	private ClientSelect selector;

	@GetMapping("/cliente/{id}")
	public  Client findClientById(@PathVariable long id) {
		List<Client> clients = repository.findAll();
		return selector.selector(clients, id);
	}
	@GetMapping("/clientes")
	public List<Client> findClients() {
		List<Client> clients = repository.findAll();
		return clients;
	}

	@PostMapping("/cadastro")
	public void createClient (@RequestBody Client client) {
		repository.save((client));
	}

	@PutMapping("/cliente/atualizar")
	public void updateClient(@RequestBody Client clientUpdate) {

		Client client = repository.getById(clientUpdate.getId());
		ClientUpdate update = new ClientUpdate();
		update.update(client, clientUpdate);
		repository.save(client);

	}

	@DeleteMapping("/cliente/excluir")
	public void deleteClient(@RequestBody Client exclude) {
		Client client = repository.getById(exclude.getId());
		repository.delete(client);
	}
}
