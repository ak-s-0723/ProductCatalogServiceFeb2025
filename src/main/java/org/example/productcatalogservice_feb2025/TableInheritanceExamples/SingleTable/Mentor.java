package org.example.productcatalogservice_feb2025.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_mentor")
@DiscriminatorValue(value="1")
public class Mentor extends User {
    Double ratings;
}
