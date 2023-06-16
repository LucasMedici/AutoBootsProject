package com.autobots.automanager.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class Client extends RepresentationModel<Client> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private String nomeSocial;
	@Column
	private Date dataNascimento;
	@Column
	private Date dataCadastro;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Document> documents = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Adress adress;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Telephone> telephones = new ArrayList<>();

}