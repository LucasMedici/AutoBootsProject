package com.autobots.automanager.services;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;


    public void create(Company company) {
        repository.save(company);
    }


    public List<Company> findAll(){
        List<Company> companies = repository.findAll();
        return companies;
    }

    public Company findById(Long id) {
        Company company = repository.findById(id).orElse(null);
        return company;
    }

    public Company update(Company companyUpdate) {
        Company companyExist = findById(companyUpdate.getId());
        companyUpdate.setId(companyExist.getId());
        companyUpdate.setRazaoSocial(companyExist.getRazaoSocial());
        companyUpdate.setCadastro(companyExist.getCadastro());
        if (companyUpdate.getServices() == null){
            companyUpdate.setServices(companyExist.getServices());
        }
        if (companyUpdate.getProducts() == null){
            companyUpdate.setProducts(companyExist.getProducts());
        }
        if (companyUpdate.getUsers() == null){
            companyUpdate.setUsers(companyExist.getUsers());
        }
        if (companyUpdate.getSells() == null){
            companyUpdate.setSells(companyExist.getSells());
        }
        if (companyUpdate.getEndereco() == null){
            companyUpdate.setEndereco(companyExist.getEndereco());
        }
        if (companyUpdate.getTelefones() == null){
            companyUpdate.setTelefones(companyExist.getTelefones());
        }

        return repository.save(companyUpdate);
    }

    public void delete(Company company) {
        repository.delete(company);
    }

}
