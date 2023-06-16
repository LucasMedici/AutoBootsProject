package com.autobots.automanager.services;

import com.autobots.automanager.entities.Services;
import com.autobots.automanager.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository repository;

    public Long create(Services services) {
        return repository.save(services).getId();
    }

    public List<Services> findAll(){
        List<Services> services = repository.findAll();
        return services;
    }

    public Services findById(Long id) {
        Services services = repository.findById(id).orElse(null);
        return services;
    }

    public Services update(Services servicesUpdate) {
        Services servicesExist = findById(servicesUpdate.getId());
        servicesUpdate.setId(servicesExist.getId());

        if (servicesUpdate.getNome() == null){
            servicesUpdate.setNome(servicesExist.getNome());
        }
        if (servicesUpdate.getDescricao() == null){
            servicesUpdate.setDescricao(servicesExist.getDescricao());
        }
        return repository.save(servicesUpdate);
    }

    public void delete(Services services) {
        repository.delete(services);
    }

}
