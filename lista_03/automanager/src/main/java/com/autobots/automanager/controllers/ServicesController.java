package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.entities.Services;
import com.autobots.automanager.entities.Sell;
import com.autobots.automanager.models.AddLinkServices;
import com.autobots.automanager.services.CompanyService;
import com.autobots.automanager.services.ServicesService;
import com.autobots.automanager.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicesController {

	@Autowired
	private ServicesService servicesService;

	@Autowired
	private SellService sellService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	private AddLinkServices addLinkServices;
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateServices(@RequestBody Services services, @PathVariable Long id){
		Services serv = servicesService.findById(id);
		if(serv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		services.setId(id);
		servicesService.update(services);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/cadastrar/{id}")
	public ResponseEntity<?> createService(@RequestBody Services services, @PathVariable Long id ){
	Company company = companyService.findById(id);
	if (company == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	company.getServices().add(services);
	companyService.create(company);
	return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Services>> findServices(){
		List<Services> services = servicesService.findAll();
		addLinkServices.linkAdd(services);
		return new ResponseEntity<List<Services>>(services, HttpStatus.FOUND);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Services> findServicesById(@PathVariable Long id){
		Services services = servicesService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(services == null) {
			status = HttpStatus.NOT_FOUND;
		}else{
			addLinkServices.linkAdd(services);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Services>(services, status);
	}	
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deleteService(@PathVariable Long id){
		Services servicesSelected = servicesService.findById(id);
		
		if(servicesSelected == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Company> companies = companyService.findAll();
	    List<Sell> sells = sellService.findAll();
	    
	    for (Company company : companies) {
	        for (Services services : company.getServices()) {
	          if (services.getId().equals(id)) {
	        	  company.getServices().remove(services);
	          }
	        }
	      }
	    
	      for (Sell sell : sells) {
	        for (Services services : sell.getServices()) {
	          if (services.getId().equals(id)) {
	        	  sell.getServices().remove(services);
	          }
	        }
	      }
	
	    servicesService.delete(servicesSelected);
		return new ResponseEntity<>(HttpStatus.OK);
	    
	}
	
}
