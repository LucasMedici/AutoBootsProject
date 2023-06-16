package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Document;
import com.autobots.automanager.models.AddLinkDocument;
import com.autobots.automanager.models.DocumentUpdate;
import com.autobots.automanager.models.DocumentSelect;
import com.autobots.automanager.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentController {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DocumentSelect selector;
    @Autowired
    private AddLinkDocument addLink;

    @GetMapping("/documentos")
    public ResponseEntity<List<Document>> findDocuments(){
        List<Document> documents = repository.findAll();
        if (documents.isEmpty()) {
            ResponseEntity<List<Document>> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return res;
        } else {
            addLink.linkAdd(documents);
            ResponseEntity<List<Document>> res = new ResponseEntity<>(documents, HttpStatus.FOUND);
            return res;
        }
    }

    @GetMapping("/documento/{id}")
    public ResponseEntity<Document> findDocumentById(@PathVariable Long id){
        List<Document> documents = repository.findAll();
        Document document =  selector.selector(documents, id);
        if (document == null) {
            ResponseEntity<Document> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return res;
        } else {
            addLink.linkAdd(document);
            ResponseEntity<Document> res = new ResponseEntity<>(document, HttpStatus.FOUND);
            return res;
        }
    }

    @PostMapping("/documento/cadastro")
    public ResponseEntity<?> createDocument(@RequestBody Document document) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (document.getId() == null) {
            repository.save(document);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/atualizar")
    public ResponseEntity<?> updateDocument(@RequestBody Document documentUpdate) {
        HttpStatus status = HttpStatus.CONFLICT;
        Document document = repository.getById(documentUpdate.getId());
        if (document != null) {
            DocumentUpdate atualizador = new DocumentUpdate();
            atualizador.update(document, documentUpdate);
            repository.save(document);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> deleteDocument(@RequestBody Document exclude) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Document document = repository.getById(exclude.getId());
        if (document != null) {
            repository.delete(document);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);

    }
}
