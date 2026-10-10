package com.vedrunaSevilla.product.controller.converter;

import org.springframework.stereotype.Component;

import com.vedrunaSevilla.product.controller.dto.ProductDto;
import com.vedrunaSevilla.product.persistance.model.Product;

@Component 
public class Converter {

    public ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setSku(product.getSku());
        return productDto;
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        return product;
    }
}
