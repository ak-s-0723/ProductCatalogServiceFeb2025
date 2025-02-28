package org.example.productcatalogservice_feb2025.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    String name;
    String description;
    String imageUrl;
    Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    Category category;
    Boolean isPrime;

    public Product() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
