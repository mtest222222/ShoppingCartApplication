package com.muru.example;

import java.math.BigDecimal;

public class LineItem {
    private final Product product;
    private final int quantity;
    private final BigDecimal price;

    private LineItem(LineItemBuilder builder){
        this.product=builder.product;
        this.quantity=builder.quantity;
        this.price=builder.price;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class LineItemBuilder{
        private final Product product;
        private int quantity;
        private BigDecimal price=BigDecimal.ZERO;

        public LineItemBuilder(Product product) {
            this.product = product;
        }

        public LineItemBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public LineItemBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public LineItem build() {
            LineItem lineItem = new LineItem(this);
            return lineItem;
        }

    }

}
