Feature: this is reqres feature

#  @reqres
  Scenario: a valid reqres
    Given i want to create "reqres" request
    When user sends create requests
    Then response is 201 okay


  @reqres
  Scenario Outline: a valid reqres
    Given i want to create "reqres" request
    And i want to update "<name>" for "<path>"
    When user sends create requests
    Then response is 201 okay

    Examples:
      | name | path   |
      | abc  | $.name |
      | xyz  | $.name |