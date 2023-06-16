package com.autobots.automanager.controllers;

import java.util.List;

import com.autobots.automanager.entities.Client;
import com.autobots.automanager.models.AddLinkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private AddLinkClient addLink;

	@GetMapping("/cliente/{id}")
	public  ResponseEntity<Client> findClientById(@PathVariable long id) {
		List<Client> clients = repository.findAll();
		Client client = selector.selector(clients, id);
		if (client == null) {
			ResponseEntity<Client> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return res;
		} else {
			addLink.linkAdd(client);
			ResponseEntity<Client> res = new ResponseEntity<Client>(client, HttpStatus.FOUND);
			return res;
		}
	}
	@GetMapping("/clientes")
	public ResponseEntity<List<Client>> findClients() {
		List<Client> clients = repository.findAll();
		if (clients.isEmpty()) {
			ResponseEntity<List<Client>> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return res;
		} else {
			addLink.linkAdd(clients);
			ResponseEntity<List<Client>> res = new ResponseEntity<>(clients, HttpStatus.FOUND);
			return res;
		}
	}

	@PostMapping("/cliente/cadastro")
	public ResponseEntity<?> createClient(@RequestBody Client client) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (client.getId() == null) {
			repository.save(client);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}

	@PutMapping("/cliente/atualizar")
	public ResponseEntity<?> updateClient(@RequestBody Client clientUpdate) {
		HttpStatus status = HttpStatus.CONFLICT;
		Client client = repository.getById(clientUpdate.getId());
		if (client != null) {
			ClientUpdate updator = new ClientUpdate();
			updator.update(client, clientUpdate);
			repository.save(client);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/cliente/excluir")
	public ResponseEntity<?> deleteClient(@RequestBody Client exclude) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Client client = repository.getById(exclude.getId());
		if (client != null) {
			repository.delete(client);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}
}
