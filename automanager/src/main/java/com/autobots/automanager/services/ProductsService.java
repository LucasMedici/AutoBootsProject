package com.autobots.automanager.services;

import com.autobots.automanager.entities.Products;
import com.autobots.automanager.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public Long create(Products create) {
        return repository.save(create).getId();
    }

    public List<Products> findAll(){
        List<Products> products = repository.findAll();
        return products;
    }

    public Products findById(Long id) {
        Products products = repository.findById(id).orElse(null);
        return products;
    }

    public Products update(Products productsUpdate) {
        Products productsExist = findById(productsUpdate.getId());
        productsUpdate.setId(productsExist.getId());
        productsUpdate.setCadastro(productsExist.getCadastro());

        if (productsUpdate.getValidade() == null){
            productsUpdate.setValidade(productsExist.getValidade());
        }
        if (productsUpdate.getFabricao() == null){
            productsUpdate.setFabricao(productsExist.getFabricao());
        }
        if (productsUpdate.getNome() == null){
            productsUpdate.setNome(productsExist.getNome());
        }
        if (productsUpdate.getDescricao() == null){
            productsUpdate.setDescricao(productsExist.getDescricao());
        }
        return repository.save(productsUpdate);
    }

    public void delete(Products products) {
        repository.delete(products);
    }


}
