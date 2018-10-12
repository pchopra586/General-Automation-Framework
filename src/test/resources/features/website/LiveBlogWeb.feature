@Web
Feature: Validate Live Blog Article URL

  @LiveBlog
  Scenario: Validate Live Blog Article URL
    Given I want to launch the Straits Times website[Live Blog]
    Then I get the total number of articles published in default view of Live Blog