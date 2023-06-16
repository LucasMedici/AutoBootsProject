package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.entities.Products;
import com.autobots.automanager.entities.User;
import com.autobots.automanager.entities.Sell;
import com.autobots.automanager.models.AddLinkProducts;
import com.autobots.automanager.services.CompanyService;
import com.autobots.automanager.services.ProductsService;
import com.autobots.automanager.services.UserService;
import com.autobots.automanager.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SellService sellService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	private AddLinkProducts addLinkProducts;
	
	@PostMapping("/cadastrar/{id}")
	public ResponseEntity<?> createProduct(@RequestBody Products products, @PathVariable Long id){
		
		Long idProduct = productsService.create(products);
		Products newProducts = productsService.findById(idProduct);
		
		User user = userService.findById(id);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		for(Company company : companyService.findAll()) {
			for (User users : userService.findAll()) {
				if(users.getId().equals(user.getId())) {
					company.getProducts().add(newProducts);
					companyService.create(company);
				}
			}
		}
		
		user.getProducts().add(newProducts);
		userService.create(user);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Products>> getAllProducts(){
		List<Products> products = productsService.findAll();
		if(products.isEmpty()) {
			return new ResponseEntity<List<Products>>(HttpStatus.NOT_FOUND);
		}
		addLinkProducts.linkAdd(products);
		return new ResponseEntity<List<Products>>(products, HttpStatus.FOUND);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable Long id){
		Products products = productsService.findById(id);
		HttpStatus status = HttpStatus.CONFLICT;
		if(products == null) {
			status = HttpStatus.NOT_FOUND;	
		}else{
			addLinkProducts.linkAdd(products);
			status = HttpStatus.FOUND;
		}
		return new ResponseEntity<Products>(products, status);
		
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateProducts(@RequestBody Products products, @PathVariable Long id){
		Products productExist = productsService.findById(id);
		if (productExist == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		products.setId(id);
		productsService.update(products);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		Products productSelected = productsService.findById(id);
	
	    if(productSelected == null) {
	    	return new ResponseEntity<>(HttpStatus.FOUND);
	    }
	    
	    List<User> users = userService.findAll();
	    List<Company> companies = companyService.findAll();
	    List<Sell> sells = sellService.findAll();
	    
	    for(User user : users) {
	    	for(Products products : user.getProducts()) {
	    		if(products.getId() == id) {
	    			user.getProducts().remove(products);
					userService.create(user);
	    		}
	    	}
	    }
	    
	    for(Company company : companies) {
	    	for(Products products : company.getProducts()) {
	    		if(products.getId() == id) {
	    			company.getProducts().remove(products);
	    			companyService.create(company);
	    		}
	    	}
	    }
	    
	    for(Sell sell : sells) {
	    	for(Products products : sell.getProducts()) {
	    		if(products.getId() == id) {
	    			sell.getProducts().remove(products);
	    			sellService.create(sell);
	    		}
	    	}
	    }

	    productsService.delete(productSelected);
	    return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
