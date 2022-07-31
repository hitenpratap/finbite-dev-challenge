package com.finbite.util;

import java.text.NumberFormat;
import java.util.Locale;

public class AppUtil {

    public static String formatCurrency(Double amount, Locale locale) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }

}
