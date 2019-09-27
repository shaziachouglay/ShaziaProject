Feature: Testing the login page for microsoft 365

  Scenario Outline: Verify the login page with valid credentials
    Given   I am on microsoft poortal portal
    When    I enter valid  "<userName>" and valid "<password>"
    Then    I must see I am successfully logged in
    Then    I wait for page load to complete
    Then    I click on Activities


    Examples:
      | userName                                     | password |
      |Customerservice@eternityservices.onmicrosoft.com|London123|
