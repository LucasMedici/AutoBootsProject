package com.autobots.automanager.models;

import com.autobots.automanager.controllers.DocumentController;
import com.autobots.automanager.entities.Document;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkDocument implements AddLink<Document> {
    @Override
    public void linkAdd(List<Document> list) {
        for (Document document : list) {
            long id = document.getId();
            Link linkk = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(DocumentController.class)
                            .findDocumentById(id))
                    .withSelfRel();
            document.add(linkk);
        }
    }

    @Override
    public void linkAdd(Document object) {
        Link linkk = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(DocumentController.class)
                        .findDocuments())
                .withRel("Documentos: ");
        object.add(linkk);
    }
}
