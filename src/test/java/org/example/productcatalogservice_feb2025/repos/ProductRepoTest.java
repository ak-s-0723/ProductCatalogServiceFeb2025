package org.example.productcatalogservice_feb2025.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;


    @Test
    public void insertIntoRDS() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone15");
        product.setPrice(1000000D);
        Category category = new Category();
        category.setId(2L);
        category.setName("Phones");
        product.setCategory(category);
        productRepo.save(product);

        Product product2 = new Product();
        product2.setId(5L);
        product2.setName("Macbook Air");
        product2.setPrice(999999D);
        Category category2 = new Category();
        category2.setId(12L);
        category2.setName("Laptops");
        product2.setCategory(category);
        productRepo.save(product2);
    }


    @Test
    @Transactional
    public void testQueries() {
//     List<Product> productList = productRepo.findProductByOrderByPrice();
//     for(Product product:productList) {
//         System.out.println(product.getName());
//     }

        System.out.println(productRepo.findCategoryNameFromProductId(1L));
    }

}