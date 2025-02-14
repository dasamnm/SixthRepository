Feature: Login to HRM Application

  Background: 
    Given User is on Home page

  @ValidCredentials
  Scenario: Login with valid credentials - Feature 1, Scenario - 1
    When User enters username as "Admin" and password as "admin123"
    And Click on login button
    Then User should be able to login successfully

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials - Feature 1, Scenario - 2
    When User enters username as "<username>" and password as "<password>"
    And Click on login button
    Then User should be able to see error message "<errorMessage>"

    Examples: 
      | username | password  | errorMessage        |
      | Admin    | admin12$$ | Invalid credentials |
    