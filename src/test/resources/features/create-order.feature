@new
Feature: Create order

  With the ambition to buy new parts, as a customer I want create a new order.

  Background: Log in as user with the correct role

    A common user who wants to create orders has the role of an orderer.

    Given I am logged in as "00446"

  Scenario: Create a new order and add parts
    Given In my shopping cart I have an order in status draft
    When I add these parts to the order and saves it
      | 445566 |
      | 988776 |

    Then the order should be displayed having 2 orderlines with the parts
      | 445566 |
      | 988776 |
