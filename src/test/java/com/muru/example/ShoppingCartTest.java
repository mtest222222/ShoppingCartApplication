package com.muru.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest {
    private ShoppingCart cart;

    @BeforeEach
    public void setup() {
        cart = new ShoppingCart.ShoppingCartBuilder().build();;
    }

    @Test
    public void isCartEmptyIfNoItemsAdded() {
        assertTrue(cart.getLineItems().isEmpty());
    }

    @Test
    public void cartCannotBeModifiedByExternalEntity() {
        assertThrows(UnsupportedOperationException.class, () -> cart.getLineItems().put("_test_", new LineItem.LineItemBuilder(new Product.ProductBuilder("_test_").build()).build()));
    }
}
