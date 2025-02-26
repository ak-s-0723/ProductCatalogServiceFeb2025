package org.example.productcatalogservice_feb2025.services;

import org.example.productcatalogservice_feb2025.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

//    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }


    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =
                restTemplate.getForEntity("http://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class,id);

        if(fakeStoreProductDto.getBody() != null &&
                fakeStoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return from(fakeStoreProductDto.getBody());
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]>  response = restTemplate.getForEntity("http://fakestoreapi.com/products/",
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<Product>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()) {
            Product product = from(fakeStoreProductDto);
            products.add(product);
        }

        return products;
    }

    public Product replaceProduct(Product product,Long id) {
     FakeStoreProductDto  input = from(product);
     FakeStoreProductDto output =
             requestForEntity("http://fakestoreapi.com/products/{id}",HttpMethod.PUT,input,
                     FakeStoreProductDto.class,id).getBody();
     return from(output);
    }



    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    private FakeStoreProductDto from(Product product) {
        //ToDo Please add implementation here
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }


}
