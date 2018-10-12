#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
    #
      #
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
	