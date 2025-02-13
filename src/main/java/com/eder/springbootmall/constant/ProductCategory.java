package com.eder.springbootmall.constant;

import java.util.Arrays;

public enum ProductCategory {

    FOOD("food"),
    CAR("car"),
    E_BOOK("ebook");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public ProductCategory fromLabel(String label) {
        return Arrays.stream(ProductCategory.values())
                .filter(e -> e.label.equals(label))
                .findFirst()
                .orElse(null);
    }
}
