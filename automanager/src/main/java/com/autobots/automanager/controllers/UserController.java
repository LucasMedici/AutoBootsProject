package com.autobots.automanager.controllers;


import com.autobots.automanager.entities.*;
import com.autobots.automanager.services.CompanyService;
import com.autobots.automanager.services.UserService;
import com.autobots.automanager.services.VehicleService;
import com.autobots.automanager.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private SellService sellService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/cadastrar/{id}")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ADMIN')")
	public ResponseEntity<?> createUser(@RequestBody User user, @PathVariable Long id){
		Company companySelect = companyService.findById(id);
		
		if(companySelect != null) {
	        if(user.getPerfis().toString().contains("FORNECEDOR")) {
	        	if(user.getProducts().size() > 0)
	        	companySelect.getProducts().addAll(user.getProducts());
	        }	
	        
	        userService.create(user);
	        
	        companySelect.getUsers().add(user);
	        companyService.create(companySelect);

	        return new ResponseEntity<> (HttpStatus.CREATED);
	        
		}
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        	
	}
	
	
	@GetMapping("/buscar")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ADMIN')")
	public ResponseEntity<List<User>> findAllUsers(){
		List<User> user = userService.findAll();
		return new ResponseEntity<List<User>>(user, HttpStatus.FOUND);
	}
	
	@GetMapping("/buscar/{id}")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('CLIENTE')")
	public ResponseEntity<User> findUserById(@PathVariable Long id){
		User user = userService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(user == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<User>(user, status);
	}
	
	@PutMapping("/cadastrarcredencial/{id}")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ADMIN')")
	public ResponseEntity<?> createCredential(@PathVariable Long id, @RequestBody CredentialUserPassword userCredential){
		User user = userService.findById(id);
		if(user == null) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.getCredenciais().add(userCredential);
		userService.create(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/deletar/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		User userSelect = userService.findById(id);
		if (userSelect == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		for (Company company : companyService.findAll()) {
			for(User employee : company.getUsers()) {
				if(employee.getId() == userSelect.getId()) {
					company.getUsers().remove(employee);
				}
			}
		}
		
		 for(Sell sell : sellService.findAll()) {
			 if(sell.getFuncionario().getId() == userSelect.getId()) {
				 sell.setFuncionario(null);
			 }
			 if(sell.getCliente().getId() == userSelect.getId()) {
				 sell.setCliente(null);
			 }
		 }
		
		for (Vehicle vehicle : vehicleService.findAll()) {
			if(vehicle.getProprietario().getId() == userSelect.getId()) {
				vehicle.setProprietario(null);
			}
		}
		
		userSelect.getDocumentos().removeAll(userSelect.getDocumentos());
		userSelect.getTelefones().removeAll(userSelect.getTelefones());
		userSelect.getEmails().removeAll(userSelect.getEmails());
		userSelect.getCredenciais().removeAll(userSelect.getCredenciais());
		userSelect.getProducts().removeAll(userSelect.getProducts());
		userSelect.getVehicles().removeAll(userSelect.getVehicles());
		userSelect.getSells().removeAll(userSelect.getSells());
		userSelect.setEndereco(null);
		
		
		userService.delete(userSelect);
		
		return new ResponseEntity<>(HttpStatus.OK);
			
		}

}
