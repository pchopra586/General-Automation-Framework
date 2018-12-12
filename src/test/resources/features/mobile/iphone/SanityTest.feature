Feature: Run basic sanity tests
  @sanity 
  Scenario: Run basic sanity tests
    Given I want to complete the basic app installation [Sanity Testing]
  		Then I capture the dimension of "Top Stories" on Home Page
  		
  	Scenario Outline: Home Page Test
    And I validate the alignment, relative dimension and duplicacy of articles in "<SectionName>" on Home Page
   
   Examples:
    |SectionName	|
    |Premium	|
    |Viewpoints	|
    |Asia Top Stories	|
    |Christmas Special|
    |ST Food				|
    |Web Specials		|
    |Entertainment	|
    
   Scenario: Article Page Alignment Test 
   	Given I open the last article on Home Page 
   	Then I validate the basic view of Article Content
   	And capture the dimension of elements on Article Detail Page
   	Then I bookmark the article in view
   	And Keep Scrolling to navigate, validate the alignment and bookmark the articles on Home Page
   	Then I go back to home page
   	
   Scenario: Bookmark Test
   	And I goto Bookmark list page
   	And verify the bookmarked articles are same as bookmarked and ordered based on latest first 
   
   
    
    