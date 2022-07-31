package com.finbite.model;

import com.finbite.enums.EntityType;
import com.finbite.enums.PackageType;
import com.finbite.exception.DataNotFound;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PackageCls {

    private PackageType packageType;
    private Double basePrice;
    private List<PackageData> packageDataList;

    public PackageData fetchPackageData(EntityType entityType) {
        return this.packageDataList.stream()
                .filter(packageData -> packageData.getEntityType() == entityType)
                .findAny().orElseThrow(() -> new DataNotFound(String.format("No data found for package %s for entity type %s", packageType, entityType)));
    }

}
