Feature: Validate Print Edition View
  @regression 
  Scenario: Validate Print Edition View
    Given I want to complete the basic app installation
    When I goto Print Edition Page
    And I see the Instant Pre-download Pop Up
    Then I close the Instant Pre-download pop up 
    And I see the Push Notification pop up
    Then I close the Push Notification pop up
    Then I validate the default view of Print Edition Page
    And I click on Calendar icon in the view
    Then I verify the Archived Print Edition dates from Calendar
	