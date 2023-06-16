package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}