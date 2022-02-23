package com.muru.example;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final String displayName;
    private final BigDecimal price;

    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.displayName = builder.displayName;
        this.price = builder.price;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class ProductBuilder {
        private final String name;
        private String displayName;
        private BigDecimal price=BigDecimal.ZERO;;

        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product product = new Product(this);
            return product;
        }
    }
}
