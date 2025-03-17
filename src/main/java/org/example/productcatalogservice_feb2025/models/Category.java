package org.example.productcatalogservice_feb2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    String name;
    String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
 //   @Fetch(FetchMode.JOIN)
//    @BatchSize(size = 2)
    List<Product> products;
}

//number of batches = n/batchsize
//total number of queries in this case = 1 + number of batches
