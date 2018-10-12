@Web
Feature: Validate Print Edition

  @PrintEdition
  Scenario: Validate Print Edition View
    Given I want to launch the Straits Times website[Print Edition]
    When I click on Print Edition link
    Then I get the total number of articles published in Print Edition