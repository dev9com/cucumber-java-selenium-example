@requires_browser
Feature: Enter a search in Google

  Scenario: Search results should display the search term in the page title when pressing "enter"
    Given A Google search page
    When I enter the search term "cats"
    And I submit the search by pressing "enter"
    Then The search result page title should contain the search term

  Scenario: Search results should display the search term in the page title when pressing "search"
    Given A Google search page
    When I enter the search term "dogs"
    And I submit the search by pressing "search"
    Then The search result page title should contain the search term

  # This essentially does the same thing as the two above scenarios: included as a technical demo
  Scenario Outline: Search results should display the search term in the title regardless of submission method
    Given A Google search page
    When I enter the search term "<search_term>"
    And I submit the search by pressing "<submission_method>"
    Then The search result page title should contain the search term

  Examples: Term and submission method combinations
    | search_term | submission_method |
    | cats        | enter             |
    | dogs        | search            |
