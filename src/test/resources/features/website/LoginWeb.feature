Feature: Login into Straits Time Website
  @loginWeb
  Scenario Outline: Login into Straits Time website
    Given I want to launch the Straits Times website
    When I want to click on the login link
    And I want to login the straits time site as <UserName> and <Password>
#    And I want to verify the user has been logged in as <UserName>
#    Then I want to verify the main article page
#    Then I want to logout StraiTimes app

    Examples:
      |UserName     |Password   |
      |Premium_access1 |Password123|