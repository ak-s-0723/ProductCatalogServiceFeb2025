package org.example.productcatalogservice_feb2025.services;

import org.example.productcatalogservice_feb2025.dtos.SortParam;
import org.example.productcatalogservice_feb2025.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize,List<SortParam> sortParams);
}
