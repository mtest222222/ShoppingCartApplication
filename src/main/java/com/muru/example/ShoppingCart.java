package com.muru.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<String, LineItem> lineItems = new HashMap();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private final BigDecimal salesTaxRate;
    private BigDecimal salesTaxAmount = BigDecimal.ZERO;

    private  ShoppingCart(ShoppingCartBuilder builder) {
        this.salesTaxRate=builder.salesTaxRate;
    }

    public BigDecimal getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<String, LineItem> getLineItems() {
        return Collections.unmodifiableMap(lineItems);
    }

    public void addLineItem(Product product, Integer quantity) {
        String productName = product.getName();
        LineItem lineItem = lineItems.getOrDefault(productName, new LineItem.LineItemBuilder(product).build());
        Integer updatedQuantity = lineItem.getQuantity() + quantity;
        BigDecimal updatedPrice = lineItem.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        LineItem updatedLineItem = new LineItem.LineItemBuilder(product).quantity(updatedQuantity).price(updatedPrice).build();
        lineItems.put(productName, updatedLineItem);
        this.totalPrice = lineItems.values().stream().map(li -> li.getPrice()).reduce(BigDecimal.ZERO, (subtotalPrice, limeItemPrice) -> subtotalPrice.add(limeItemPrice));
        calculateTaxesAndUpdatedTotalPrice();
    }

    private void calculateTaxesAndUpdatedTotalPrice() {
        this.salesTaxAmount = salesTaxRate.multiply(totalPrice).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        this.totalPrice = this.totalPrice.add(this.salesTaxAmount).setScale(2, RoundingMode.HALF_UP);
    }

    public static class ShoppingCartBuilder {
        private BigDecimal salesTaxRate=BigDecimal.ZERO;

        public ShoppingCartBuilder salesTaxRate(BigDecimal salesTaxRate) {
            this.salesTaxRate = salesTaxRate;
            return this;
        }

        public ShoppingCart build() {
            ShoppingCart shoppingCart = new ShoppingCart(this);
            return shoppingCart;
        }
    }
}
