package com.autobots.automanager.models;

import com.autobots.automanager.controllers.AdressController;
import com.autobots.automanager.entities.Adress;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkAdress implements AddLink<Adress> {
    @Override
    public void linkAdd(List<Adress> list) {
        for (Adress adress : list) {
            long id = adress.getId();
            Link linkk = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(AdressController.class)
                            .findAdressById(id))
                    .withSelfRel();
            adress.add(linkk);
        }
    }

    @Override
    public void linkAdd(Adress object) {
        Link linkk = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(AdressController.class)
                        .findAdress())
                .withRel("Endereços: ");
        object.add(linkk);
    }
}
