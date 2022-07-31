package com.finbite.model;

import com.finbite.enums.EntityType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageData {

    private EntityType entityType;
    private Integer entityCount;

}
