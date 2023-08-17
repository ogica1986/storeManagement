package com.storemgnmt.controller;

import com.storemgnmt.entities.Product;
import com.storemgnmt.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.storemgnmt.controller.HttpBasicAuthenticationWebSecurityConfigurerAdapter.ADMIN;
import static com.storemgnmt.controller.HttpBasicAuthenticationWebSecurityConfigurerAdapter.CLIENT;

@RestController
@RequestMapping("/storeMgmt/products")
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    private ProductService productService;

    @GetMapping
    @Secured(CLIENT)
    public List<Product> findAllProducts(){
        logger.info("findAllProducts was called");
        return productService.findAllProducts();
    }

    @PostMapping
    @Secured(ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Product product){
        logger.info("saveProduct was called");
        productService.addProduct(product);
    }

    @PatchMapping("/{productName}")
    @Secured(ADMIN)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void changePrice(@PathVariable String productName,@RequestBody BigDecimal price){
        logger.info("changePrice was called");
        productService.changePrice(productName,price);
    }

    @GetMapping("/{productName}")
    @Secured(CLIENT)
    public Product findProductByName(@PathVariable String productName){
       logger.info("findProductByName was called");
       return productService.findProductByName(productName);
    }
}
