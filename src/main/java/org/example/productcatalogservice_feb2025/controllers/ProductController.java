package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.CategoryDto;
import org.example.productcatalogservice_feb2025.dtos.ProductDto;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    //@Qualifier("sps")
    private IProductService productService;

//    @Autowired
//    @Qualifier("fkps")
//    private IProductService productService2;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product : products) {
           productDtos.add(from(product));
        }

        return productDtos;
    }

    @GetMapping("/{productId}/{userId}")
    public ProductDto getProductDetailsBasedOnUserRole(@PathVariable
                                                           Long productId,
                                                       @PathVariable Long
                                                               userId) {
        Product product = productService.getProductBasedOnUserRole(productId,userId);
        if(product != null) {
            return from(product);
        }

        return null;
    }

    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id) {
        if(id < 0) {
            throw new IllegalArgumentException("Please pass productId greater than 0");
        }else if(id == 0) {
            throw new IllegalArgumentException("Please pass positive productId");
        }
        //id++;
        Product product = productService.getProductById(id);
        if(product == null) return null;
        return from(product);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product input = from(productDto);
        Product response = productService.createProduct(input);
        return from(response);
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,
                              @RequestBody ProductDto productDto) {
        return null;
    }

    @DeleteMapping("/{id}")
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
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return  product;
    }
}
