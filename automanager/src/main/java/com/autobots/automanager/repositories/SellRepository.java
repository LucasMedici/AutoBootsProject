package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellRepository extends JpaRepository<Sell,Long > {

}
