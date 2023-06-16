package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

}
