package org.example.productcatalogservice_feb2025.services;

import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
       Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
       return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        return productRepo.save(input);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty()) {
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional =
                productRepo.findById(product.getId());

        if(productOptional.isEmpty()) {
            return productRepo.save(product);
        }else {
            return productOptional.get();
        }
    }
}
