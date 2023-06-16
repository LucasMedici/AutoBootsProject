package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
