Feature: Run basic sanity tests
  @sanity 
  Scenario: Run basic sanity tests
    Given I want to complete the basic app installation [Sanity Testing]
  		Then I capture the dimension of "Top Stories" on Home Page
  		
  	Scenario Outline: Test
    And I validate the alignment and relative dimension of "<SectionName>" on Home Page
   	
   Examples:
    |SectionName	|
    |Viewpoints	|
    |Asia Top Stories	|
    |ST Food				|
    |Web Specials		|
    |Entertainment	|
    
   Scenario: Bookmark Tests
   	Given I open the last article on Home Page 
   	Then I validate the basic view of Article Content
   	And capture the dimension of elements on Article Detail Page
   #	Then I bookmark the article in view
   #	And Keep Scrolling to navigate and bookmark the articles on Home Page
   #
   
   
    #Then I validate the alignment and title of each articles in Home Page 
    #Then I goto STNow Tab in Home Page View
    #When I open each article in STNow
    #Then I validate all articles are opened with respective titles and publish dates
    #When I navigate till end of STNow section
    #Then I open Recap hyperlink
    #And Ensure it opens the previous day articles
    #Then Click on Back button
    #And Goto Home Tab in Home Page View
    
    