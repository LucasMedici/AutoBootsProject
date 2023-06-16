package com.autobots.automanager.models;

import com.autobots.automanager.controllers.TelephoneController;
import com.autobots.automanager.entities.Telephone;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkTel implements AddLink<Telephone> {

    @Override
    public void linkAdd(List<Telephone> list) {
        for (Telephone telephone : list) {
            long id = telephone.getId();
            Link linkk = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(TelephoneController.class)
                            .findTelephonesById(id))
                    .withSelfRel();
            telephone.add(linkk);
        }
    }

    @Override
    public void linkAdd(Telephone object) {
        Link linkk = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(TelephoneController.class)
                        .findTelephones())
                .withRel("Telefones: ");
        object.add(linkk);
    }

}
