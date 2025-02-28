package org.example.productcatalogservice_feb2025.services;

import org.example.productcatalogservice_feb2025.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProduct(Product input,Long id);

    Boolean deleteProduct(Long id);

    Product createProduct(Product product);
}
