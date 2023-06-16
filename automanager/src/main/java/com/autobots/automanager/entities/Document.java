package com.autobots.automanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
public class Document extends RepresentationModel<Document> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String tipo;
	@Column(unique = true)
	private String numero;
}