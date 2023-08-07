package com.storemgnmt.controller;
import java.math.BigDecimal;
import java.util.List;
import com.storemgnmt.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.storemgnmt.service.ProductService;

@RestController()
@RequestMapping("/storeMgmt/products")
public class StoreController {

    Logger logger = LoggerFactory.getLogger(StoreController.class);


    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> findAllProducts(){
        logger.info("findAllProducts was called");
        return productService.findAllProducts();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Product product){
        logger.info("saveProduct was called");
        productService.addProduct(product);
    }

    @PutMapping("/{productName}")
    public void changePrice(@PathVariable String productName,@RequestBody BigDecimal price){
        logger.info("changePrice was called");
        productService.changePrice(productName,price);
    }

    @GetMapping("/{productName}")
    public Product findProductByName(@PathVariable String productName){
       logger.info("findProductByName was called");
       return productService.findProductByName(productName);
    }
}
