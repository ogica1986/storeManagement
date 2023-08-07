package com.storemgnmt.service;

import com.storemgnmt.entities.Product;
import com.storemgnmt.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void testFindAllProducts(){
        List<Product> expectedList = List.of(new Product(1L, "prod1", 10, BigDecimal.TEN));
        when(productRepository.findAll()).thenReturn(expectedList);
        List<Product> actualList = productService.findAllProducts();
        assertIterableEquals(actualList,expectedList,"Expected product list is not returned");
        verify(productRepository).findAll();
    }

    @DisplayName("Should be able to search repo by name and handle null and not null result")
    @ParameterizedTest()
    @EnumSource(FindProductByProductName.class)
    void testFindProductByProductName(FindProductByProductName testCase){
        when(productRepository.findProductByName(testCase.productName)).thenReturn(testCase.result);
        if (testCase.exception){
            assertThrows(ProductNotFoundException.class,() -> productService.findProductByName(testCase.productName));
        }else{
            Product result=productService.findProductByName(testCase.productName);
            assertEquals(result,testCase.result);
        }
        verify(productRepository).findProductByName(testCase.productName);
    }

    @DisplayName("Should be able to save new product")
    @ParameterizedTest()
    @EnumSource(SaveProductTestCase.class)
    void testAddProduct(SaveProductTestCase testCase){
        when(productRepository.findProductByName(testCase.result.getName())).thenReturn(testCase.exception ? testCase.result:null);
        if (testCase.exception) {
            assertThrows(ProductExistingException.class,() -> productService.addProduct(testCase.result));
            verify(productRepository, times(0)).save(testCase.result);
        }else{
            productService.addProduct(testCase.result);
            verify(productRepository).save(testCase.result);
        }

    }


    private enum FindProductByProductName{
        EXISTING_VALUE("prod1",new Product(1L, "prod1", 10, BigDecimal.TEN),false),
        NULL_VALUE("prod2",null,true)
        ;

        FindProductByProductName(String productName,Product result, Boolean exception) {
            this.productName = productName;
            this.result = result;
            this.exception = exception;
        }
        private final Product result;
        private final boolean exception;

        private final String productName;
    }

    private enum SaveProductTestCase{
        EXISTING_VALUE(new Product(1L, "prod1", 10, BigDecimal.TEN),false),
        NULL_VALUE(new Product(2L, "prod2", 10, BigDecimal.TEN),true)
        ;

        SaveProductTestCase(Product result, Boolean exception) {
            this.result = result;
            this.exception = exception;
        }
        private final Product result;
        private final boolean exception;

    }
}
