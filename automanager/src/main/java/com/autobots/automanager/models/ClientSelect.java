package com.autobots.automanager.models;

import java.util.List;

import com.autobots.automanager.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientSelect {
	public Client selector(List<Client> clients, long id) {
		Client selected = null;
		for (Client client : clients) {
			if (client.getId() == id) {
				selected = client;
			}
		}
		return selected;
	}
}