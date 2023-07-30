package com.storemgnmt.service;

import com.storemgnmt.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.storemgnmt.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductByName(String name){
        return Optional.ofNullable(productRepository.findProductByName(name)).orElseThrow(ProductNotFoundException::new);
    }

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
