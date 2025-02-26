package org.example.productcatalogservice_feb2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    String name;
    String description;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
