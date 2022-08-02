package com.finbite;

import com.finbite.enums.EntityType;
import com.finbite.enums.PackageType;
import com.finbite.exception.DataNotFound;
import com.finbite.model.PackageCls;
import com.finbite.model.PackageData;
import com.finbite.model.Price;
import com.finbite.util.AppUtil;
import lombok.Data;

import java.util.*;

@Data
public class Driver {

    private List<PackageCls> packageClsList;
    private List<Price> priceList;
    private final Locale locale = new Locale("en", "EE");

    public Driver() {
        this.packageClsList = this.createPackages();
        this.priceList = this.createPrices();
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        Double invoiceSmallTotal = driver.calculateInvoiceTotal(new HashMap<EntityType, Integer>() {{
            put(EntityType.MINUTES, 11);
            put(EntityType.SMS, 49);
        }}, PackageType.SMALL);
        System.out.println("\n");
        Double invoiceMediumTotal = driver.calculateInvoiceTotal(new HashMap<EntityType, Integer>() {{
            put(EntityType.MINUTES, 38);
        }}, PackageType.MEDIUM);
        System.out.println("\n");
        Double invoiceLargeTotal = driver.calculateInvoiceTotal(new HashMap<EntityType, Integer>() {{
            put(EntityType.MINUTES, 777);
            put(EntityType.SMS, 369);
        }}, PackageType.LARGE);
    }

    protected Double calculateInvoiceTotal(Map<EntityType, Integer> usage, PackageType packageType) {
        PackageCls packageCls = this.getPackageClsList().stream().filter(obj -> obj.getPackageType() == packageType)
                .findFirst().orElseThrow(() -> new DataNotFound(String.format("No data found for package %s", packageType)));
        Double totalAmount = packageCls.getBasePrice();
        System.out.println(String.format("************************ Package %s ************************", packageType));
        System.out.println("Service Name\t\t\t\tAmount Due");
        System.out.println(String.format("%-10s%33s", "Base Price", AppUtil.formatCurrency(packageCls.getBasePrice(), locale)));
        for (Map.Entry<EntityType, Integer> entry : usage.entrySet()) {
            Price price = this.getPriceList().stream().filter(obj -> obj.getEntityType() == entry.getKey())
                    .findFirst().orElseThrow(() -> new DataNotFound(String.format("No price data found for entity type %s", entry.getKey())));
            PackageData packageData = packageCls.fetchPackageData(entry.getKey());
            int excessUsage = entry.getValue() - packageData.getEntityCount();
            double excessAmount = 0.0;
            if (excessUsage > 0) {
                excessAmount = excessUsage * price.getUnitPrice();
            }
            totalAmount += excessAmount;
            System.out.println(String.format("%-10s%33s", entry.getKey(), AppUtil.formatCurrency(excessAmount, locale)));
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println(String.format("Total Amount %30s", AppUtil.formatCurrency(totalAmount, locale)));
        return totalAmount;
    }

    private List<Price> createPrices() {
        List<Price> priceList = new ArrayList<>();
        Price minutePrice = Price.builder()
                .entityType(EntityType.MINUTES)
                .unitPrice(0.2)
                .build();
        Price smsPrice = Price.builder()
                .entityType(EntityType.SMS)
                .unitPrice(0.3)
                .build();
        priceList.add(smsPrice);
        priceList.add(minutePrice);
        return priceList;
    }

    private List<PackageCls> createPackages() {
        List<PackageCls> packageClsList = new ArrayList<>();
        PackageCls smallPackage = PackageCls.builder()
                .packageType(PackageType.SMALL)
                .basePrice(5.0)
                .packageDataList(Arrays.asList(this.createPackageData(EntityType.MINUTES, 10),
                        this.createPackageData(EntityType.SMS, 50)))
                .build();
        packageClsList.add(smallPackage);

        PackageCls mediumPackage = PackageCls.builder()
                .packageType(PackageType.MEDIUM)
                .basePrice(10.0)
                .packageDataList(Arrays.asList(this.createPackageData(EntityType.MINUTES, 50),
                        this.createPackageData(EntityType.SMS, 100)))
                .build();
        packageClsList.add(mediumPackage);

        PackageCls largePackage = PackageCls.builder()
                .packageType(PackageType.LARGE)
                .basePrice(20.0)
                .packageDataList(Arrays.asList(this.createPackageData(EntityType.MINUTES, 500),
                        this.createPackageData(EntityType.SMS, 500)))
                .build();
        packageClsList.add(largePackage);
        return packageClsList;
    }

    private PackageData createPackageData(EntityType entityType, Integer entityCount) {
        return PackageData.builder().entityType(entityType)
                .entityCount(entityCount)
                .build();
    }
}
