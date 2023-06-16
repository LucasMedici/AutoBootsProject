package com.autobots.automanager.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class Company extends RepresentationModel<Company> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String razaoSocial;
	@Column
	private String nomeFantasia;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Telephone> telefones = new HashSet<>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Adress endereco;
	@Column(nullable = false)
	private Date cadastro;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<User> users = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Products> products = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Services> services = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Sell> sells = new HashSet<>();
}