package com.autobots.automanager.services;

import com.autobots.automanager.entities.Sell;
import com.autobots.automanager.repositories.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {

    @Autowired
    private SellRepository repository;

    public Long create(Sell sell) {
        return repository.save(sell).getId();
    }

    public List<Sell> findAll(){
        List<Sell> sells = repository.findAll();
        return sells;
    }

    public Sell findById(Long id) {
        Sell sell = repository.findById(id).orElse(null);
        return sell;
    }

    public Sell update(Sell sellUpdate) {
        Sell sellExist = findById(sellUpdate.getId());
        sellUpdate.setId(sellExist.getId());
        sellUpdate.setCadastro(sellExist.getCadastro());
        sellUpdate.setIdentificacao(sellExist.getIdentificacao());
        sellUpdate.setFuncionario(sellExist.getFuncionario());

        if (sellUpdate.getProducts() == null){
            sellUpdate.setProducts(sellExist.getProducts());
        }
        if (sellUpdate.getServices() == null){
            sellUpdate.setServices(sellExist.getServices());
        }
        if (sellUpdate.getVehicle() == null){
            sellUpdate.setVehicle(sellExist.getVehicle());
        }
        return repository.save(sellUpdate);
    }

    public void delete(Sell sell) {
        repository.delete(sell);
    }
}
