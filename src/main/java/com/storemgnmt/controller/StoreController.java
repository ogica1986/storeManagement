package com.storemgnmt.controller;
import java.util.List;
import com.storemgnmt.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.storemgnmt.service.ProductService;

@RestController

public class StoreController {

    @Autowired
    private ProductService productService;

    @GetMapping("/storeMgmt/products")
    public List<Product> findAllProducts(){
        return productService.findAllProducts();
    }

    @PostMapping("/storeMgmt/products")
    public void saveProduct(Product product){
        productService.addProduct(product);
    }

    @GetMapping("/storeMgmt/products/{productName}")
    public Product findProductByName(@PathVariable String productName){
       return productService.findProductByName(productName);
    }
}
