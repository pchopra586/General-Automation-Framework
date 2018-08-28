Feature: Login into Straits Time Website
  @loginWeb
  Scenario Outline: Login into Straits Time website
    Given I want to launch the Straits Times website
    When I want to click on the login link
    And I want to login the straits time site as <UserName> and <Password>
    And I want to verify the user has been logged in as <UserName>
    And I want to verify and read the main article has a picture or video
    And I want to click the main article
    Then I want to verify the main article page

    Examples:
      |UserName     |Password   |
      |Premium_access1 |Password123|