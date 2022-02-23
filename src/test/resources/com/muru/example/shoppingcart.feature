Feature: Shopping Cart.

  Scenario: Adding an item to an empty order
    Given An empty shopping cart and a product,_DoveSoap_ with a unit price of _39.99_
    When the user adds 1 _DoveSoap_ to the shopping cart
    Then the shopping cart should contain a single line item with 1 DoveSoap with a unit price of _39.99_
    And the shopping cart's total price should equal _39.99_
    And totals should be rounded up-to 2 decimal places


  Scenario: Adding many products
    Given An empty shopping cart and a product,_DoveSoap_ with a unit price of _39.99_
    When the user adds 5 _DoveSoap_ to the shopping cart
    And the user adds 3 _DoveSoap_ to the shopping cart
    Then the shopping cart should contain a single line item
    And the shopping cart should contain 8 DoveSoaps each with a unit price of _39.99_
    And the shopping cart's total price should equal _319.92_
    And totals should be rounded up-to 2 decimal places

  Scenario: Calculate tax rate with many products
    Given An empty shopping cart and a product,_DoveSoap_ with a unit price of _39.99_
    And a product,_AxeDeo_ with a unit price of _99.99_
    And a sales tax rate of 12.5% applicable to all products equally
    When the user adds 2 _DoveSoap_ to the shopping cart
    And the user adds 2 _AxeDeo_ to the shopping cart
    Then the shopping cart should contain 2 DoveSoaps each with a unit price of _39.99_
    And the shopping cart should contain 2 AxeDeos each with a unit price of _99.99_
    And the total sales tax amount for the shopping cart should equal _35.00_
    And the shopping cart's total price should equal _314.96_
    And totals should be rounded up-to 2 decimal places