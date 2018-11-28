Feature: Article title verification at home page 
  I want to verify article title of home page articles.

 @regression 
  Scenario: Validate article title on home page
    Given I want to complete basic app installation new
    When I captured the article title 
    And I click on first article at the home page
    Then I am at article details page
    And Verify that article with same title is opened
  