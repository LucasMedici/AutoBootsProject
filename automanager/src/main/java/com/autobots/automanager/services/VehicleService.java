package com.autobots.automanager.services;

import com.autobots.automanager.entities.Vehicle;
import com.autobots.automanager.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public Long create(Vehicle vehicle) {
        return repository.save(vehicle).getId();
    }

    public List<Vehicle> findAll(){
        List<Vehicle> vehicles = repository.findAll();
        return vehicles;
    }

    public Vehicle findById(Long id) {
        Vehicle vehicle = repository.findById(id).orElse(null);
        return vehicle;
    }

    public Vehicle update(Vehicle vehicleUpdate) {
        Vehicle vehicleExist = findById(vehicleUpdate.getId());
        vehicleUpdate.setId(vehicleExist.getId());

        if (vehicleUpdate.getTipo() == null){
            vehicleUpdate.setTipo(vehicleExist.getTipo());
        }
        if (vehicleUpdate.getModelo() == null){
            vehicleUpdate.setModelo(vehicleExist.getModelo());
        }
        if (vehicleUpdate.getPlaca() == null){
            vehicleUpdate.setPlaca(vehicleExist.getPlaca());
        }
        if (vehicleUpdate.getProprietario() == null){
            vehicleUpdate.setProprietario(vehicleExist.getProprietario());
        }
        if (vehicleUpdate.getSells() == null){
            vehicleUpdate.setSells(vehicleExist.getSells());
        }
        return repository.save(vehicleUpdate);
    }

    public void delete(Vehicle vehicle) {
        repository.delete(vehicle);
    }
}
