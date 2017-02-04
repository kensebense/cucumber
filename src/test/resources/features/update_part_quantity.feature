@norun
Feature: Modify a submitted order

  In order to give users flexibility in the orders process,
  as a customer it should be possible to modify submitted orders

  A user with the role of an orderer can create and sumbit orders. When an
  order is submitted, it is still possible for the user to modify that order until it
  reaches the status PICKING_STARTED.

  Background: Log in as user with the correct role and submit an order

    Given I am logged in as "orderer"
    And In my shopping cart I have an order in status draft
    And I add these parts to the order and saves it
      | 445566 |
      | 988776 |
    And I submit the order with shipping address
      | firstName | lastName | street          | city      | postalCode | countryName    |
      | Peter     | Fish     | Prince's street | Edinburgh | 12345      | United Kingdom |

  Scenario: Modify quantity of a part in an order
    When I modify quantity for part "445566" to "5"
    Then the order is displayed with correct part details
      | 445566 | 5 |
      | 988776 | 1 |

  Scenario: Remove a part in an order
    When I remove part "988776" from order
    Then the order is displayed with correct part details
      | 445566 | 1 |
