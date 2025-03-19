package org.example.productcatalogservice_feb2025.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_feb2025.dtos.ProductDto;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetProducts_RunSuccessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Macbook");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        when(productService.getAllProducts()).thenReturn(products);


        ProductDto productDto = new ProductDto();
        productDto.setName("Iphone");
        productDto.setId(1L);
        ProductDto productDto2= new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Macbook");
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        productDtos.add(productDto2);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtos)));
    }

    @Test
    public void Test_CreateProduct_RunSuccessfully() throws Exception {
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setPrice(100000D);
        productDto.setName("SuperComputer");

        Product product = new Product();
        product.setId(10L);
        product.setPrice(100000D);
        product.setName("SuperComputer");
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        //Act and Assert
        mockMvc.perform(post("/products")
                        .content(objectMapper.writeValueAsString(productDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)));
    }
}
