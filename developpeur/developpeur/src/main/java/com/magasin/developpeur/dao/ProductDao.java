package com.magasin.developpeur.dao;

import com.magasin.developpeur.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    Product findById(int id);

    List<Product> findByPriceGreaterThan(int price);
}
