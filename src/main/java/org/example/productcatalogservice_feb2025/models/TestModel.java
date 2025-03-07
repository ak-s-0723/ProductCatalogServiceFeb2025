package org.example.productcatalogservice_feb2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TestModel extends BaseModel {
    private Integer numField;
}
