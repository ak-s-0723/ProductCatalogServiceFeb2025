package org.example.productcatalogservice_feb2025.repos;

import jakarta.transaction.Transactional;
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
    @Transactional
    public void testQueries() {
//     List<Product> productList = productRepo.findProductByOrderByPrice();
//     for(Product product:productList) {
//         System.out.println(product.getName());
//     }

        System.out.println(productRepo.findCategoryNameFromProductId(1L));
    }

}