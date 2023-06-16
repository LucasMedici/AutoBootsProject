package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.entities.User;
import com.autobots.automanager.entities.Vehicle;
import com.autobots.automanager.entities.Sell;
import com.autobots.automanager.models.AddLinkSell;
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
@RequestMapping("/venda")

public class SellController {

	@Autowired
	private SellService sellService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddLinkSell linkSell;
	
	@PostMapping("/cadastrar/{id}")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('VENDEDOR')")
	public ResponseEntity<?> createSell(@RequestBody Sell sell, @PathVariable Long id){
		Company companySelected = companyService.findById(id);
		if(companySelected == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User employeesSelected = userService.findById(sell.getFuncionario().getId());
		User userSelected = userService.findById(sell.getCliente().getId());
		Vehicle vehicleSelected = vehicleService.findById(sell.getVehicle().getId());
		sell.setVehicle(vehicleSelected);
		sell.setCliente(userSelected);
		sell.setFuncionario(employeesSelected);
		userService.create(employeesSelected);

		companySelected.getSells().add(sell);
		companyService.create(companySelected);

		sellService.create(sell);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/buscar")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ADMIN')")
	public ResponseEntity<List<Sell>> findAllSells(){
		List<Sell> sell = sellService.findAll();
		linkSell.linkAdd(sell);
		return new ResponseEntity<List<Sell>>(sell, HttpStatus.FOUND);
	}
	
	@GetMapping("/buscar/{id}")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ADMIN') or hasAnyAuthority('VENDEDOR') or hasAnyAuthority('CLIENTE')")
	public ResponseEntity<Sell> findSellById(@PathVariable Long id){
		Sell sell = sellService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(sell == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			linkSell.linkAdd(sell);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Sell>(sell, status);
	}

	@PutMapping("/atualizar/{id}")
	@PreAuthorize("hasAnyAuthority('GERENTE') or hasAnyAuthority('ADMIN')")
		public ResponseEntity<?> updateSell(@RequestBody Sell sell, @PathVariable Long id){
			Sell sellExist = sellService.findById(id);
			if (sellExist == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			sell.setId(id);
			sellService.update(sell);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	
	
	
}
