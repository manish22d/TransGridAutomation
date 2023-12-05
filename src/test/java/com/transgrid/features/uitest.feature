Feature: this is reqres feature

  @ui
  Scenario: verify title of login page
    Given user logs in to transgrid
    When user get page title
    Then page title should be "IBM Maximo Application Suite"
