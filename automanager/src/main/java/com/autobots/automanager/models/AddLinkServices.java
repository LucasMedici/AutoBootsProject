package com.autobots.automanager.models;

import com.autobots.automanager.controllers.ServicesController;
import com.autobots.automanager.entities.Services;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkServices implements AddLink<Services> {

	@Override
	public void linkAdd( List<Services> list ) {
		for (Services services : list) {
			Link linkService =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServicesController.class)
							.findServicesById(services.getId()))
					.withSelfRel();
			services.add(linkService);
			
		}
	}
	@Override
	public void linkAdd( Services services) {
			Link linkService =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ServicesController.class)
							.findServices())
					.withRel("Serviços");
			services.add(linkService);
			
}
}
