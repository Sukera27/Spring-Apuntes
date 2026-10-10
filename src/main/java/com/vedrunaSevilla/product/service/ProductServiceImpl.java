package com.vedrunaSevilla.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedrunaSevilla.product.persistance.model.Product;
import com.vedrunaSevilla.product.persistance.repository.ProductRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRespository productRespository;

    @Override
    public List<Product> getAllProducts() {
        return productRespository.findAll();
    }

    @Override 
    public Product getProductById(Long id) {
        return productRespository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRespository.save(product);
    }
    @Override 
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRespository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        return productRespository.save(existingProduct);
    }
    @Override 
    public void deleteProduct(Long id) {
        Product existingProduct = productRespository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRespository.delete(existingProduct);
    }

}
