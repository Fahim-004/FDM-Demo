package com.example.productdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.productdemo.entity.Product;
import com.example.productdemo.repository.ProductRepository;
import com.example.productdemo.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	 @Autowired
	    private ProductRepository productRepository;

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String showProducts(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("products", service.searchProducts(keyword));
        } else {
            model.addAttribute("products", service.getAllProducts());
        }

        model.addAttribute("keyword", keyword);
        return "products";
    }
    
    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable int id, Model model) {

        Product product = productRepository.findById(id).orElse(null);

        model.addAttribute("product", product);
        return "product-details";
    }
}