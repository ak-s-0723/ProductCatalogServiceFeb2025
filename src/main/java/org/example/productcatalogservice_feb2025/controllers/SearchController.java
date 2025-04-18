package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.SearchRequestDto;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping("/search")
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
       return searchService.searchProducts(searchRequestDto.getQuery(),
               searchRequestDto.getPageNumber(),
               searchRequestDto.getPageSize(),searchRequestDto.getSortParams());
    }
}


/*
{
    "query" : "laptop",
    "pageSize" : 10,
    "pageNumber" : 0,
    "sortParams" : [
        {
            "paramName": "price",
            "sortType" : "DESC"
        }
        ,
        {
            "paramName" : "id",
             "sortType": "DESC"
        }
    ]
}
 */