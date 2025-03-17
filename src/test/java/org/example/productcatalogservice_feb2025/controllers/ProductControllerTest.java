package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.ProductDto;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    private IProductService productService;

    @Autowired
    private ProductController productController;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void TestGetProductDetailsById_WithValidProductId_RunSuccessfully() {
        //Arrange
        Long id  = 3L;
        Product product = new Product();
        product.setId(3L);
        product.setName("Iphone");
        product.setPrice(100000D);
       when(productService.getProductById(id)).thenReturn(product);

        //Act
        ProductDto productDto = productController.getProductDetails(id);

        //Assert
        assertNotNull(productDto);
        assertEquals(productDto.getName(),"Iphone");
        assertEquals(productDto.getId(),id);
        assertEquals(productDto.getPrice(),100000D);
    }


    @Test
    public void TestGetProductDetailsById_WithNegativeProductId_ResultsInIllegalArgumentException() {
       //   Act and Assert

        //productController.getProductDetails(-1L);

       Exception exception = assertThrows(IllegalArgumentException.class,
               ()->productController.getProductDetails(-5L));
       assertEquals(exception.getMessage(),"Please pass productId greater than 0");

       verify(productService,times(0)).getProductById(-5L);
    }

    @Test
    public void TestGetProductDetailsById_ProductServiceCalledWithCorrectArguments_RunSuccessfully() {
        //Arrange
        Long productId = 5L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Macbook");
        when(productService.getProductById(productId)).thenReturn(product);

        //Act
        productController.getProductDetails(productId);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(productId,idCaptor.getValue());

    }



}