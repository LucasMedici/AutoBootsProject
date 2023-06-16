package com.autobots.automanager.models;

import com.autobots.automanager.controllers.UserController;
import com.autobots.automanager.entities.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkUser implements AddLink<User> {
	@Override
	public void linkAdd( List<User> list ) {
		for (User client : list) {
			Link linkClient =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserController.class)
							.findUserById(client.getId()))
					.withSelfRel();
			client.add(linkClient);
		}
	}
	
	@Override
	public void linkAdd( User user) {
			Link linkClient =  WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserController.class)
							.findAllUsers())
					.withRel("");
			user.add(linkClient);
	}
}
