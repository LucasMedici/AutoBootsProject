package com.autobots.automanager.models;

import com.autobots.automanager.controllers.ClientController;
import com.autobots.automanager.entities.Client;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkClient implements AddLink<Client> {

    @Override
    public void linkAdd(List<Client> list) {
        for (Client client : list) {
            long id = client.getId();
            Link linkk = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(ClientController.class)
                            .findClientById(id))
                    .withSelfRel();
            client.add(linkk);
        }
    }

    @Override
    public void linkAdd(Client object) {
        Link linkk = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(ClientController.class)
                        .findClients())
                .withRel("Clientes: ");
        object.add(linkk);
    }
}