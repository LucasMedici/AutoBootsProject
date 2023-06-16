package com.autobots.automanager.controllers;

import java.util.Date;
import java.util.List;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.models.AddLinkCompany;
import com.autobots.automanager.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/empresa")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AddLinkCompany companyLink;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> createCompany(@RequestBody Company company){
		company.setCadastro(new Date());
		companyService.create(company);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Company>> getAllCompanies(){
		List<Company> all = companyService.findAll();
		HttpStatus status = HttpStatus.CONFLICT;
		if(all.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
			return new ResponseEntity<List<Company>>(status);
		}else {
			status = HttpStatus.FOUND;
			ResponseEntity<List<Company>> res = new ResponseEntity<List<Company>>(all, status);
			companyLink.linkAdd(all);
			return res;
		}
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
		Company company = companyService.findById(id);
		if(company == null) {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}else {
			companyLink.linkAdd(company);
			return new ResponseEntity<Company>(company, HttpStatus.FOUND);
		}
	}
	
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company){
		Company companyNow = companyService.findById(id);
		if(companyNow == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		company.setId(id);
		companyService.update(companyNow);
		companyService.create(company);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable Long id){
		Company SelectedCompany = companyService.findById(id);
		if(SelectedCompany == null) {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
		companyService.delete(SelectedCompany);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}