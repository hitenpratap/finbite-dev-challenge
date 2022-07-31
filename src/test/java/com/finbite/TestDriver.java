package com.finbite;


import com.finbite.enums.EntityType;
import com.finbite.enums.PackageType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestDriver {

    private Driver driver;

    @Before
    public void setup() {
        this.driver = new Driver();
    }

    @Test
    public void testCalculateInvoiceTotal_1() {
        //when package is SMALL and usage is under the limit
        double invoiceTotal = driver.calculateInvoiceTotal(new HashMap<EntityType, Integer>() {{
            put(EntityType.MINUTES, 10);
            put(EntityType.SMS, 49);
        }}, PackageType.SMALL);
        assertEquals(5.0, invoiceTotal, 0);
    }

    @Test
    public void testCalculateInvoiceTotal_2() {
        //when package is SMALL and usage is over the limit
        double invoiceTotal = driver.calculateInvoiceTotal(new HashMap<EntityType, Integer>() {{
            put(EntityType.MINUTES, 11);
            put(EntityType.SMS, 49);
        }}, PackageType.SMALL);
        assertEquals(5.2, invoiceTotal, 0);
    }

}
