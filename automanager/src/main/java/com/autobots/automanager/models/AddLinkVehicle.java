package com.autobots.automanager.models;

import java.util.List;

import com.autobots.automanager.entities.Vehicle;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.VehicleController;



@Component
public class AddLinkVehicle implements AddLink <Vehicle>{
	
	@Override
	public void linkAdd( List<Vehicle> list ) {
		for (Vehicle vehicle : list) {
			Link linkVehicle =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VehicleController.class)
							.findVehicleById(vehicle.getId()))
					.withSelfRel();
			vehicle.add(linkVehicle);
			
		}
	}
	@Override
	public void linkAdd( Vehicle vehicle) {
			Link linkVehicle =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VehicleController.class)
							.findAllVehicles())
					.withRel("Veiculos");
			vehicle.add(linkVehicle);
			
}
	}
