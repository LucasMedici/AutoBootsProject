package com.autobots.automanager.models;

import java.util.List;

import com.autobots.automanager.entities.Products;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.ProductsController;

@Component
public class AddLinkProducts implements AddLink<Products> {
	
	@Override
	public void linkAdd (List<Products> list) {
		for (Products products : list) {
			Link linkProducts = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ProductsController.class)
							.getProductById(products.getId()))
					.withSelfRel();
			products.add(linkProducts);
		}
	}
	
	@Override
	public void linkAdd( Products products) {
		Link linkProducts =  WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ProductsController.class)
						.getAllProducts())
				.withRel("Produtos");
		products.add(linkProducts);
}
}
