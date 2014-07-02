Feature: Basic Math

  Scenario:   Addition of two numbers
    Given The number "1"
    When "1" is added the total
    Then The result is "2"

  Scenario:   Addition of two numbers incorrectly
    Given The number "2"
    When "3" is added the total
    Then The result is "5"