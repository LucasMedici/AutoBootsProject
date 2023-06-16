package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Document;
import com.autobots.automanager.models.DocumentSelect;
import com.autobots.automanager.models.DocumentUpdate;
import com.autobots.automanager.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentController {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DocumentSelect select;

    @GetMapping("/documentos")
    public List<Document> findDocuments(){
        List<Document> documents = repository.findAll();
        return documents;
    }

    @GetMapping("/documento/{id}")
    public Document findDocumentById(@PathVariable Long id){
        List<Document> document = repository.findAll();
        return select.selector(document, id);
    }

    @PostMapping("/documento")
    public void createDocument(@RequestBody Document document) {
        repository.save(document);
    }


    @PutMapping("/atualizar")
    public void updateDocument(@RequestBody Document toUpdate) {
        Document documento = repository.getById(toUpdate.getId());
        DocumentUpdate updator = new DocumentUpdate();
        updator.update(documento, toUpdate);
        repository.save(documento);
    }

    @DeleteMapping("/excluir")
    public void excludeDocument(@RequestBody Document exclude) {
        Document document = repository.getById(exclude.getId());
        repository.delete(document);
    }
}