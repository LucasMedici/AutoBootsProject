package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
