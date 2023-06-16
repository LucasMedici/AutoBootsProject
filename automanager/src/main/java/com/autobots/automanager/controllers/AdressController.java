package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Adress;
import com.autobots.automanager.models.AdressSelect;
import com.autobots.automanager.models.AdressUpdate;
import com.autobots.automanager.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Endereco")
public class AdressController {
    @Autowired
    private AdressRepository repository;

    @Autowired
    private AdressSelect select;

    @GetMapping("/enderecos")
    public List<Adress> findAdress(){
        List<Adress> adress = repository.findAll();
        return adress;
    }

    @GetMapping("/endereco/{id}")
    public Adress findAdressById(@PathVariable Long id){
        List<Adress> adress = repository.findAll();
        return select.selector(adress, id);
    }

    @PostMapping("/endereco")
    public void createAdress(@RequestBody Adress adress) {
        repository.save(adress);
    }


    @PutMapping("/endereco")
    public void updateAdress(@RequestBody Adress toUpdate) {
        Adress endereco = repository.getById(toUpdate.getId());
        AdressUpdate updateor = new AdressUpdate();
        updateor.update(endereco, toUpdate);
        repository.save(endereco);
    }

    @DeleteMapping("/excluir")
    public void deleteAdress(@RequestBody Adress exclude) {
        Adress adress = repository.getById(exclude.getId());
        repository.delete(adress);
    }
}