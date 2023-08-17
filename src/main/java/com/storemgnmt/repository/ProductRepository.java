package com.storemgnmt.repository;

import com.storemgnmt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findProductByName(String productName);

}
