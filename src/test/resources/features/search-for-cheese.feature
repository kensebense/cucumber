@old
Feature: Search Google

  With the ambition to buy smelly cheese, as a user I want search Google for it.

  Scenario: Access Google and search for cheese
    Given I have started a Chrome browser
    When I search for "Cheese!"
    Then the title bar should say "Cheese! - Sök på Google"
