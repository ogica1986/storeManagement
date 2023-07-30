package com.storemgnmt.repository;

import com.storemgnmt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findProductByName(String productName);

}
