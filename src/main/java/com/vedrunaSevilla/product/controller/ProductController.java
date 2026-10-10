package com.vedrunaSevilla.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedrunaSevilla.product.controller.converter.Converter;
import com.vedrunaSevilla.product.controller.dto.ProductDto;
import com.vedrunaSevilla.product.persistance.model.Product;
import com.vedrunaSevilla.product.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping ("/api/v1/products")
@CrossOrigin 
@AllArgsConstructor 
public class ProductController {

    ProductService productService;

    Converter converter;

    @GetMapping 
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts().stream().map(converter::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping ("/")
    public ResponseEntity<List<ProductDto>> getAllProducts2() {
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : productService.getAllProducts()) {
            ProductDto productDto = converter.convertToDto(product);
            productDtos.add(productDto);
        }
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDto productDto = converter.convertToDto(product);
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping 
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = converter.convertToEntity(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertToDto(productService.createProduct(product)));
    }
    @PutMapping ("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = converter.convertToEntity(productDto);
        return ResponseEntity.ok(converter.convertToDto(productService.updateProduct(id, product)));
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();          
    }

}
