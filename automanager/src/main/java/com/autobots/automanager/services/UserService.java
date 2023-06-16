package com.autobots.automanager.services;

import com.autobots.automanager.entities.User;
import com.autobots.automanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Long create(User user) {
        return repository.save(user).getId();
    }

    public List<User> findAll(){
        List<User> users = repository.findAll();
        return users;
    }

    public User findById(Long id) {
        User user = repository.findById(id).orElse(null);
        return user;
    }

    public User update(User userUpdate) {
        User userExist = findById(userUpdate.getId());
        userUpdate.setId(userExist.getId());

        if (userUpdate.getNome() == null){
            userUpdate.setNome(userExist.getNome());
        }
        if (userUpdate.getNomeSocial() == null){
            userUpdate.setNomeSocial(userExist.getNomeSocial());
        }
        if (userUpdate.getPerfis() == null){
            userUpdate.setPerfis(userExist.getPerfis());
        }
        if (userUpdate.getTelefones() == null){
            userUpdate.setTelefones(userExist.getTelefones());
        }
        if (userUpdate.getEndereco() == null){
            userUpdate.setEndereco(userExist.getEndereco());
        }
        if (userUpdate.getDocumentos() == null){
            userUpdate.setDocumentos(userExist.getDocumentos());
        }
        if (userUpdate.getEmails() == null){
            userUpdate.setEmails(userExist.getEmails());
        }
        if (userUpdate.getCredenciais() == null){
            userUpdate.setCredenciais(userExist.getCredenciais());
        }
        if (userUpdate.getProducts() == null){
            userUpdate.setProducts(userExist.getProducts());
        }
        if (userUpdate.getSells() == null){
            userUpdate.setSells(userExist.getSells());
        }
        if (userUpdate.getVehicles() == null){
            userUpdate.setVehicles(userExist.getVehicles());
        }
        return repository.save(userUpdate);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
