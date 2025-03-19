package org.example.productcatalogservice_feb2025.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    Long id;
    String name;
    String description;
    String imageUrl;
    Double price;
    CategoryDto category;
}
