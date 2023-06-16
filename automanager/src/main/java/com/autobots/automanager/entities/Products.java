package com.autobots.automanager.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class Products extends RepresentationModel<Products> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date validade;
	@Column(nullable = false)
	private Date fabricao;
	@Column(nullable = false)
	private Date cadastro;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private long quantidade;
	@Column(nullable = false)
	private double valor;
	@Column()
	private String descricao;
}