package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Document;
import com.autobots.automanager.entities.Telephone;
import com.autobots.automanager.models.TelephoneSelect;
import com.autobots.automanager.models.TelephoneUpdate;
import com.autobots.automanager.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelephoneController {

    @Autowired
    private TelephoneRepository repository;

    @Autowired
    private TelephoneSelect selector;

    @GetMapping("/telefones")
    public List<Telephone> findTel(){
        List<Telephone> tels = repository.findAll();
        return tels;
    }

    @GetMapping("/telefone/{id}")
    public Telephone findTelById(@PathVariable Long id){
        List<Telephone> tel = repository.findAll();
        return selector.selector(tel, id);
    }

    @PostMapping("/telefone")
    public void createTel(@RequestBody Telephone tel) {
        repository.save(tel);
    }


    @PutMapping("/atualizar")
    public void updateTel(@RequestBody Telephone toUpdate) {
        Telephone tel = repository.getById(toUpdate.getId());
        TelephoneUpdate updator = new TelephoneUpdate();
        updator.update(tel, toUpdate);
        repository.save(tel);
    }

    @DeleteMapping("/excluir")
    public void deleteDocument(@RequestBody Document exclude) {
        Telephone tel = repository.getById(exclude.getId());
        repository.delete(tel);
    }
}