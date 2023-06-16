package com.autobots.automanager.models;

import com.autobots.automanager.controllers.CompanyController;
import com.autobots.automanager.entities.Company;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkCompany implements AddLink<Company> {

	@Override
	public void linkAdd(List<Company> list) {
		for (Company company : list) {
			Link linkCompany = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(CompanyController.class)
							.findCompanyById(company.getId()))
					.withSelfRel();
			company.add(linkCompany);
		}
	}

	@Override
	public void linkAdd(Company company) {
		Link linkCompany = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(CompanyController.class)
						.getAllCompanies())
				.withRel("Empresas");
		company.add(linkCompany);
	}
}
