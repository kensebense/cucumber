@norun
Feature: Attach shipping address to order

  For an order to be complete, a shipping address must have been associated with it.
  As a customer, I should be able to specify my shipping address, and I should also be able to
  change it unless the order has been picked and sent.

  Background:

    Given I am logged in as "orderer"
    And In my shopping cart I have an order in status draft
    And I add these parts to the order and saves it
      | 445566 |

  Scenario: Submit order with shipping address
    Given I submit the order with shipping address
      | firstName | lastName | street          | city      | postalCode | countryName    |
      | Peter     | Fish     | Prince's street | Edinburgh | 12345      | United Kingdom |
    Then shipping address in the order is displayed as
      | firstName | lastName | street          | city      | postalCode | countryName    |
      | Peter     | Fish     | Prince's street | Edinburgh | 12345      | United Kingdom |

  Scenario: Change an existing shipping address on a submitted order

    Given I submit the order with shipping address
      | firstName | lastName | street          | city      | postalCode | countryName    |
      | Peter     | Fish     | Prince's street | Edinburgh | 12345      | United Kingdom |
    When I change the shipping address to
      | firstName | lastName | street           | city     | postalCode | countryName |
      | Anna      | Donkle   | Fastningsvagen 1 | Goteborg | 678953     | Sweden      |
    Then shipping address in the order is displayed as
      | firstName | lastName | street           | city     | postalCode | countryName |
      | Anna      | Donkle   | Fastningsvagen 1 | Goteborg | 678953     | Sweden      |

  Scenario Outline: Change an existing shipping address to an incomplete address
    I should not be able to enter an address that is not complete

    Given I submit the order with shipping address
      | firstName | lastName | street          | city      | postalCode | countryName    |
      | Peter     | Fish     | Prince's street | Edinburgh | 12345      | United Kingdom |

    When I change the shipping address to "<firstName>", "<lastName>", "<street>", "<city>", "<postalCode>", "<countryName>"
    Then I should see an error message about "<Outcome>"

  Examples:
    | firstName | lastName | street          | city      | postalCode | countryName    | Outcome                  |
    |           | Fish     | Prince's street | Edinburgh | 12345      | United Kingdom | First name is required   |
    | Peter     |          | Prince's street | Edinburgh | 12345      | United Kingdom | Last name is required    |
    | Peter     | Fish     |                 | Edinburgh | 12345      | United Kingdom | Street is required       |
    | Peter     | Fish     | Prince's street |           | 12345      | United Kingdom | City is required         |
    | Peter     | Fish     | Prince's street | Edinburgh |            | United Kingdom | Postal code is required  |
    | Peter     | Fish     | Prince's street | Edinburgh | 12345      |                | Country name is required |
