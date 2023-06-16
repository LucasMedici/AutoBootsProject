package com.autobots.automanager.entities;


import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CredentialBarCode extends Credential {
	@Column(nullable = false, unique = true)
	private long codigo;
}