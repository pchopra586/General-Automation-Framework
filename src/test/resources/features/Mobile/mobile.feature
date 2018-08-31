
Feature: Login into Straits Time Mobile App
  @mobileApp
  Scenario: Login into Straits Time Mobile App
    Given I want to launch the Straits Time site and accept the terms and conditions
    When I want to skip the tutorials
    And I want to click the hamburger menu
    And I want to enter the login credentials
    And I want to click the continue button
