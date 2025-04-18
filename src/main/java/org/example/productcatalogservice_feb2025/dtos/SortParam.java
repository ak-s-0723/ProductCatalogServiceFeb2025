package org.example.productcatalogservice_feb2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SortParam {
    private String paramName;

    private SortType sortType;
}
