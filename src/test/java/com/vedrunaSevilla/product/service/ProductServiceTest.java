package com.vedrunaSevilla.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vedrunaSevilla.product.persistance.model.Product;
import com.vedrunaSevilla.product.persistance.repository.ProductRespository;


@ExtendWith (MockitoExtension.class)
public class ProductServiceTest {

    @Mock 
    private ProductRespository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testGetAllProducts() {
        
        List<Product> products = new ArrayList<Product>();

           Product product1 = new Product();
           product1.setName("Product 1");
           product1.setPrice(10.0);
           product1.setDescription("Description 1");
           product1.setSku("SKU1");

            Product product2 = new Product();
            product2.setName("Product 2");
            product2.setPrice(20.0);
            product2.setDescription("Description 2");
            product2.setSku("SKU2");
            products.add(product1);
            products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        verify(productRepository).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setDescription("Description 1");
        product.setSku("SKU1"); 

        Optional<Product> optionalProduct = Optional.of(product);

        when(productRepository.findById(1L)).thenReturn(optionalProduct); 

        Product result = productService.getProductById(1L);

        assertEquals("Product 1", result.getName());
        verify(productRepository).findById(1L);
    }
    @Test 
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setDescription("Description 1");
        product.setSku("SKU1"); 

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals("Product 1", result.getName());
        verify(productRepository).save(product);
    }

    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product();
        existingProduct.setName("Product 1");
        existingProduct.setPrice(10.0);
        existingProduct.setDescription("Description 1");
        existingProduct.setSku("SKU1");     

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(20.0);
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setSku("Updated SKU");   

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);   

        Product result = productService.updateProduct(1L, updatedProduct);

        assertEquals("Updated Product", result.getName());
        assertEquals(20.0, result.getPrice());
        verify(productRepository).findById(1L);
        verify(productRepository).save(existingProduct);
    }
    @Test 
    void testDeleteProduct() {
        Product existingProduct = new Product();
        existingProduct.setName("Product 1");
        existingProduct.setPrice(10.0);
        existingProduct.setDescription("Description 1");
        existingProduct.setSku("SKU1");     

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        productService.deleteProduct(1L);

        verify(productRepository).findById(1L);
        verify(productRepository).delete(existingProduct);
    }


 
}
