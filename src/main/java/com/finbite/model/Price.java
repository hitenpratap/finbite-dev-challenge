package com.finbite.model;

import com.finbite.enums.EntityType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {

    private EntityType entityType;
    private Double unitPrice;

}
