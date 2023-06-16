package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Telephone;
import com.autobots.automanager.models.AddLinkTel;
import com.autobots.automanager.models.TelephoneUpdate;
import com.autobots.automanager.models.TelephoneSelect;
import com.autobots.automanager.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelephoneController {

    @Autowired
    private TelephoneRepository repository;

    @Autowired
    private TelephoneSelect selector;

    @Autowired
    private AddLinkTel addLink;

    @GetMapping("/telefones")
    public ResponseEntity<List<Telephone>> findTelephones(){
        List<Telephone> telephones = repository.findAll();
        if (telephones.isEmpty()) {
            ResponseEntity<List<Telephone>> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return res;
        } else {
            addLink.linkAdd(telephones);
            ResponseEntity<List<Telephone>> res = new ResponseEntity<>(telephones, HttpStatus.FOUND);
            return res;
        }
    }

    @GetMapping("/telefone/{id}")
    public ResponseEntity<Telephone> findTelephonesById(@PathVariable Long id){
        List<Telephone> telephones = repository.findAll();
        Telephone telephone = selector.selector(telephones, id);
        if (telephone == null) {
            ResponseEntity<Telephone> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return res;
        } else {
            addLink.linkAdd(telephone);
            ResponseEntity<Telephone> res = new ResponseEntity<>(telephone, HttpStatus.FOUND);
            return res;
        }
    }

    @PostMapping("/telefone")
    public ResponseEntity<?> createTelephone(@RequestBody Telephone telephone) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (telephone.getId() == null) {
            repository.save(telephone);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/atualizar")
    public ResponseEntity<?> updateTelephone(@RequestBody Telephone telUpdate) {
        HttpStatus status = HttpStatus.CONFLICT;
        Telephone telephone = repository.getById(telUpdate.getId());
        if (telephone != null) {
            TelephoneUpdate atualizador = new TelephoneUpdate();
            atualizador.update(telephone, telUpdate);
            repository.save(telephone);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> deleteTelephone(@RequestBody Telephone exclude) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Telephone telephone = repository.getById(exclude.getId());
        if (telephone != null) {
            repository.delete(telephone);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
