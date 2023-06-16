package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
