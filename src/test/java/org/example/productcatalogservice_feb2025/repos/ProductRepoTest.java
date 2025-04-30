package org.example.productcatalogservice_feb2025.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.models.Scope;
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
    public void insertDummyDataIntoDB() {
        Product product = new Product();
        product.setId(93L);
        product.setName("Melody Toffee");
        product.setPrice(1D);
        product.setScope(Scope.LISTED);
        Category category = new Category();
        category.setId(200L);
        category.setName("Toffees");
        product.setCategory(category);

        productRepo.save(product);
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