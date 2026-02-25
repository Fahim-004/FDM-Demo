package com.example.productdemo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.productdemo.entity.Product;
import com.example.productdemo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        return repo.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                keyword, keyword);
    }
}