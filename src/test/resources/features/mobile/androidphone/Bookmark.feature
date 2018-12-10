Feature: Bookmark article to read later

 @regression 
  Scenario: Verify article is saved to read later
    Given I want to complete basic app installation [Bookmark]
    When I click on premium article at the home page [Bookmark]
    Then I am at article details page [Bookmark]
    When I click on bookmark icon [Bookmark]
    Then Bookmark icon is highlighted [Bookmark]
    When I click on back button [Bookmark]
    Then We are navigated back to the homepage [Bookmark]
    When I open hamburgerMenu [Bookmark]
    Then Click on bookmark link [Bookmark]
    And  verify screen title [Bookmark]
    When I open bookmarked premium article [Bookmark]
    Then Verify section Title [Bookmark] 
    
    Scenario: Verify access of Bookmarked Premium Article
    And Verify premium article is loaded with limited access [Bookmark]

    
    Scenario: Verify Bookmark Is Deleted from bookmarked section   
    When I click on bookmark icon [Bookmark]
    And I click on back button [Bookmark]
    Then Verify default Bookmarkpage is displayed [Bookmark]
   
    Scenario: Verify access of Bookmarked free Article 
		When I open hamburgerMenu [Bookmark]
		Then I click on HomePage link [Bookmark]
    When I click on free article at the home page [Bookmark]
    Then I am at article details page [Bookmark]
    When I click on bookmark icon [Bookmark]
    Then Bookmark icon is highlighted [Bookmark]
    When I click on back button [Bookmark]
    Then We are navigated back to the homepage [Bookmark]
    When I open hamburgerMenu [Bookmark]
    Then Click on bookmark link [Bookmark]
    And  verify screen title [Bookmark]
    When I open bookmarked free article [Bookmark]
    Then Verify free article is accessible to anonymous user [Bookmark]
    
    				
		
   