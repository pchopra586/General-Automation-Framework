Feature: Login into Straits Time Mobile App
  @mobileApp 
  Scenario Outline: Login into Straits Time Mobile App
    Given I want to launch the Straits Time site and accept the terms and conditions
    When I want to skip the tutorials
    And I want to click the hamburger menu
    And I want to enter the login credentials as "<UserName>" and "<Password>"
    Then I want to verify the logged-in user as "<LoggedInUser>"
    Then I want to goto Settings menu
    Then I want to goto Account Settings
    Then I want to logout the user
	
	Examples:
    |UserName     |Password   |LoggedInUser	|
    |Premium_Access1 |Password123|Premium_Access1	|