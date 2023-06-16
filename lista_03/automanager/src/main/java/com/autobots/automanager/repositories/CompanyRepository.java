package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}