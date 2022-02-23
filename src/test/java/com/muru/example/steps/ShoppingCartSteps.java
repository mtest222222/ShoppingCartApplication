package com.muru.example.steps;


import com.muru.example.LineItem;
import com.muru.example.Product;
import com.muru.example.ShoppingCart;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartSteps {
    ShoppingCart cart;
    Product doveSoapProduct;
    Product axeDeoProduct;
    BigDecimal salesTaxRate;

    @Given("An empty shopping cart and a product,_DoveSoap_ with a unit price of _{double}_")
    public void empty_shopping_cart_and_single_dove_soap_product(Double unitPrice) {
        cart = new ShoppingCart.ShoppingCartBuilder().build();
        doveSoapProduct = new Product.ProductBuilder("_DoveSoap_").displayName("Dove Soap").price(BigDecimal.valueOf(unitPrice)).build();

    }

    @When("the user adds {int} _DoveSoap_ to the shopping cart")
    public void the_user_adds__dove_soaps_to_the_shopping_cart(Integer quantity) {
        cart.addLineItem(doveSoapProduct, quantity);
    }

    @When("the user adds {int} _AxeDeo_ to the shopping cart")
    public void the_user_adds__axe_deo_to_the_shopping_cart(Integer quantity) {
        cart.addLineItem(axeDeoProduct, quantity);
    }

    @When("then adds another {int} _DoveSoaps_ to the shopping cart")
    public void then_adds_another__dove_soaps_to_the_shopping_cart(Integer quantity) {
        cart.addLineItem(doveSoapProduct, quantity);
    }

    @Then("the shopping cart should contain a single line item")
    public void the_shopping_cart_should_contain_a_single_line_item() {
        assertEquals(1, cart.getLineItems().size());
    }

    @Then("the shopping cart should contain a single line item with {int} DoveSoap with a unit price of _{double}_")
    public void the_shopping_cart_should_contain_a_single_line_item_with_dove_soap_with_a_unit_price_of(Integer expectedQuantity, Double expectedPrice) {
        assertEquals(1, cart.getLineItems().size());
        LineItem actualLineItem = cart.getLineItems().get(doveSoapProduct.getName());
        assertEquals(expectedQuantity, actualLineItem.getQuantity());
        assertEquals(BigDecimal.valueOf(expectedPrice),actualLineItem.getProduct().getPrice());
        assertEquals(BigDecimal.valueOf(expectedPrice),actualLineItem.getPrice());
    }

    @Then("the shopping cart should contain {int} DoveSoaps each with a unit price of _{double}_")
    public void the_shopping_cart_should_contain_dove_soaps_each_with_a_unit_price_of(Integer expectedQuantity, Double expectedPrice) {
        LineItem actualLineItem = cart.getLineItems().get(doveSoapProduct.getName());
        assertEquals(expectedQuantity, actualLineItem.getQuantity());
        assertEquals(BigDecimal.valueOf(expectedPrice),actualLineItem.getProduct().getPrice());
    }

    @Then("the shopping cart's total price should equal _{double}_")
    public void the_shopping_cart_s_total_price_should_equal(Double expectedTotalPrice) {
        assertEquals(BigDecimal.valueOf(expectedTotalPrice),cart.getTotalPrice());
    }

    @Then("totals should be rounded up-to {int} decimal places")
    public void totals_should_be_rounded_up_to_decimal_places(Integer expectedRounding) {
        assertEquals(expectedRounding,Math.max(0, cart.getTotalPrice().stripTrailingZeros().scale()));
    }

    @Given("a product,_AxeDeo_ with a unit price of _{double}_")
    public void a_product__axe_deo_with_a_unit_price_of(Double unitPrice) {
        axeDeoProduct = new Product.ProductBuilder("_AxeDeo_").displayName("Axe Deodorant").price(BigDecimal.valueOf(unitPrice)).build();
    }
    @Given("a sales tax rate of {double}% applicable to all products equally")
    public void a_sales_tax_rate_of_applicable_to_all_products_equally(Double salesTaxRate) {
        cart = new ShoppingCart.ShoppingCartBuilder().salesTaxRate(BigDecimal.valueOf(salesTaxRate)).build();
    }

    @Then("the shopping cart should contain {int} AxeDeos each with a unit price of _{double}_")
    public void the_shopping_cart_should_contain_axe_deos_each_with_a_unit_price_of(Integer expectedQuantity, Double expectedPrice) {
        LineItem actualLineItem = cart.getLineItems().get(axeDeoProduct.getName());
        assertEquals(expectedQuantity, actualLineItem.getQuantity());
        assertEquals(BigDecimal.valueOf(expectedPrice),actualLineItem.getProduct().getPrice());
    }
    @Then("the total sales tax amount for the shopping cart should equal _{double}_")
    public void the_total_sales_tax_amount_for_the_shopping_cart_should_equal(Double expectedTaxAmount) {
        assertEquals(BigDecimal.valueOf(expectedTaxAmount).setScale(2, RoundingMode.HALF_UP),cart.getSalesTaxAmount());
    }


}
