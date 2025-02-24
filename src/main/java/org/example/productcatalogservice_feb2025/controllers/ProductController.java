package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.CategoryDto;
import org.example.productcatalogservice_feb2025.dtos.ProductDto;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product : products) {
           productDtos.add(from(product));
        }

        return productDtos;
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductDetails(@PathVariable Long id) {
        if(id < 0) {
            throw new IllegalArgumentException("Please pass productId greater than 0");
        }
        Product product = productService.getProductById(id);
        if(product == null) return null;
        return from(product);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return null;
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,
                              @RequestBody ProductDto productDto) {
        return null;
    }

    @DeleteMapping("/products/{id}")
    public Boolean deleteProduct(@PathVariable Long id) {
        return null;
    }



    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        return  product;
    }
}
