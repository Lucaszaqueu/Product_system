package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getAllProducts(@NotNull Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/list_product";
    }

    @GetMapping("/new")
    public String newProductForm(@NotNull Model model) {
        model.addAttribute("product", new Product());
        return "/index";
    }

    @PostMapping("/save")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products/";
    }

    @GetMapping ("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/new";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, @NotNull Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update_product.html";
    }
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
        @PostMapping ("/{productId}")
    public String updateProduct(@PathVariable Long productId, Product updatedProduct) {
        Product updated = productService.updateProduct(productId, updatedProduct);
            return "redirect:/products/";

    }
}