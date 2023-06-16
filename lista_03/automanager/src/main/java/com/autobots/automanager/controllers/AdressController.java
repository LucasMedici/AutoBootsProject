package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Adress;
import com.autobots.automanager.models.AddLinkAdress;
import com.autobots.automanager.models.AdressUpdate;
import com.autobots.automanager.models.AdressSelect;
import com.autobots.automanager.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Endereco")
public class AdressController {
    @Autowired
    private AdressRepository repository;

    @Autowired
    private AdressSelect selector;

    @Autowired
    private AddLinkAdress addLink;


    @GetMapping("/enderecos")
    public ResponseEntity<List<Adress>> findAdress(){
        List<Adress> adresses = repository.findAll();
        if (adresses.isEmpty()) {
            ResponseEntity<List<Adress>> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return res;
        } else {
            addLink.linkAdd(adresses);
            ResponseEntity<List<Adress>> res = new ResponseEntity<>(adresses, HttpStatus.FOUND);
            return res;
        }
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<Adress> findAdressById(@PathVariable Long id){
        List<Adress> adresses = repository.findAll();
        Adress adress = selector.selector(adresses, id);
        if (adress == null) {
            ResponseEntity<Adress> res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return res;
        } else {
            addLink.linkAdd(adress);
            ResponseEntity<Adress> res = new ResponseEntity<>(adress, HttpStatus.FOUND);
            return res;
        }
    }

    @PostMapping("/endereco")
    public ResponseEntity<?> createAdress(@RequestBody Adress adress) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (adress.getId() == null) {
            repository.save(adress);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(status);
    }


    @PutMapping("/endereco")
    public ResponseEntity<?> updateAdress(@RequestBody Adress updateInAdress) {
        HttpStatus status = HttpStatus.CONFLICT;
        Adress adress = repository.getById(updateInAdress.getId());
        if (adress != null) {
            AdressUpdate updator = new AdressUpdate();
            updator.update(adress, updateInAdress);
            repository.save(adress);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);

    }

    @DeleteMapping("/excluir")
    public ResponseEntity<?> deleteAdress(@RequestBody Adress exclude) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Adress adress = repository.getById(exclude.getId());
        if (adress != null) {
            repository.delete(adress);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }
}
