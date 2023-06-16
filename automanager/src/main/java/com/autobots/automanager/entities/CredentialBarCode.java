package com.autobots.automanager.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CredentialBarCode extends Credential {
	@Column(nullable = false, unique = true)
	private long codigo;
}