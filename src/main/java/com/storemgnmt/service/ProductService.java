package com.storemgnmt.service;

import com.storemgnmt.entities.Product;
import org.springframework.stereotype.Service;
import com.storemgnmt.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductByName(String name){
        return Optional.ofNullable(productRepository.findProductByName(name)).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void changePrice(String productName, BigDecimal price){
        Optional.ofNullable(findProductByName(productName)).ifPresent( product ->product.setUnitaryPrice(price));
    }

    @Transactional
    public void addProduct(Product newProduct){
        Optional<Product> existingProdOpt=Optional.ofNullable (
                productRepository.findProductByName(newProduct.getName()));
        if (existingProdOpt.isPresent()){
            throw new ProductExistingException("Product " + newProduct + " is already added in the database");
        }else {
            productRepository.save(newProduct);
        }
    }
}
