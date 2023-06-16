package com.autobots.automanager.entities;


import java.util.Date;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credential {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date criadoEm;
	@Column()
	private Date ultimoAcesso;
	@Column(nullable = false)
	private boolean atividade;
}