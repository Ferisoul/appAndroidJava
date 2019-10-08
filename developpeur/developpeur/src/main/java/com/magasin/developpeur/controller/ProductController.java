package com.magasin.developpeur.controller;

import com.magasin.developpeur.dao.ProductDao;
import com.magasin.developpeur.exception.ProductNotFoundException;
import com.magasin.developpeur.model.Product;
import com.magasin.developpeur.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Gestion des produits")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @ApiOperation(value = "Affiche tous les produits")
    @GetMapping(value = "/Products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @ApiOperation(value = "Affiche un produit selon l'id")
    @GetMapping(value = "/Products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product", "id", productId));
    }

    @ApiOperation(value = "Enregistre un produit")
    @PostMapping(value = "/Products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @ApiOperation(value = "Met à jour un Produit")
    @PutMapping("/Products/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails)
    {
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product", "id", productId));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    @ApiOperation(value = "supprime un Produit")
    @DeleteMapping("/Products/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable(value = "id") Long productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product", "id", productId));

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}