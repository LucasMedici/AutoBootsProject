package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.User;
import com.autobots.automanager.entities.Vehicle;
import com.autobots.automanager.entities.Sell;
import com.autobots.automanager.models.AddLinkVehicle;
import com.autobots.automanager.services.UserService;
import com.autobots.automanager.services.VehicleService;
import com.autobots.automanager.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private SellService sellService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AddLinkVehicle addLinkVehicle;
	
	@PostMapping("cadastrar/{id}")
	public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle, @PathVariable Long id){
		User user = userService.findById(id);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			user.getVehicles().add(vehicle);
			vehicle.setProprietario(user);
			vehicleService.create(vehicle);
			userService.create(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Vehicle>> findAllVehicles(){
		List<Vehicle> vehicle = vehicleService.findAll();
		addLinkVehicle.linkAdd(vehicle);
		return new ResponseEntity<List<Vehicle>>(vehicle, HttpStatus.FOUND);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Vehicle> findVehicleById(@PathVariable Long id){
		Vehicle vehicle = vehicleService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(vehicle == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			addLinkVehicle.linkAdd(vehicle);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Vehicle>(vehicle, status);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle, @PathVariable Long id){
		Vehicle vec = vehicleService.findById(id);
		if (vec == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vehicle.setId(id);
		vehicleService.update(vehicle);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
		Vehicle vehicleSelect = vehicleService.findById(id);
		if(vehicleSelect == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<User> users = userService.findAll();
		List<Sell> sells = sellService.findAll();
		
		for(User user : users) {
			for(Vehicle vehicle : user.getVehicles()) {
				if(vehicle.getId() == id) {
					user.getVehicles().remove(vehicle);
					userService.create(user);
				}
			}
		}
		
		for(Sell sell : sells) {
				if(sell.getVehicle().getId() == id) {
					sell.setVehicle(null);
					sellService.create(sell);
				}
			}

		vehicleService.delete(vehicleSelect);
		return new ResponseEntity<>(HttpStatus.OK);
		
		}
		
	
}

