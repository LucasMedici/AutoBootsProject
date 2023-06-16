package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.CredentialUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredentialRepository extends JpaRepository<CredentialUserPassword, Long> {

}
