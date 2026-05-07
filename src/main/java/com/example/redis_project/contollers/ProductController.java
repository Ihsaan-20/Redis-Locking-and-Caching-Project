package com.example.redis_project.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.redis_project.models.Product;
import com.example.redis_project.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
    // Is API ko hit karne par pehli baar DB hit hoga, 
    // dusri baar se data direct Redis se aayega.
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductDetails(id);
    }

    // Isse hum naya product add karenge (Ya manual SQL bhi use kar sakte hain)
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
