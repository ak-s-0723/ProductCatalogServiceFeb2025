package org.example.productcatalogservice_feb2025.services;

import org.example.productcatalogservice_feb2025.dtos.Role;
import org.example.productcatalogservice_feb2025.dtos.UserDto;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.models.Scope;
import org.example.productcatalogservice_feb2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("sps")
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RedisTemplate<String,Object>  redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {
        Product product = (Product)redisTemplate.opsForHash().get("products",id);

        if(product == null) {
            Optional<Product> optionalProduct = productRepo.findById(id);
            if(optionalProduct.isPresent()) {
                redisTemplate.opsForHash().put(
                        "products", id, optionalProduct.get());

                return optionalProduct.get();
            }

            return null;
        }

        return product;
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

    @Override
    public Product getProductBasedOnUserRole(Long productId, Long userId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(product.getScope().equals(Scope.LISTED)) {
                return product;
            }else if(product.getScope().equals(Scope.UNLISTED)) {
                //Call UserService and get User Details
                UserDto userDto = restTemplate.getForEntity("http://userservice/user/{userId}", UserDto.class,userId)
                        .getBody();

                if(userDto.getRole().equals(Role.ADMIN)) {
                    return product;
                }

            }
        }

        return null;
    }
}
