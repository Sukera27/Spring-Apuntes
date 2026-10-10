package com.vedrunaSevilla.product.controller.dto;


import lombok.Data;

@Data 
public class ProductDto {
    
    private String name;

    private double price;

    private String description;
    
    private String sku;
}
