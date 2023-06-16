package com.autobots.automanager.entities;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String endereco;
}