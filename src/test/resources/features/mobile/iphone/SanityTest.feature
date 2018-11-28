Feature: Run basic sanity tests
  @sanity 
  Scenario Outline: Run basic sanity tests
    Given I want to complete the basic app installation [Sanity Testing]
    Then I capture the dimension of "<SectionName1>" on Home Page
    And I validate the alignment and relative dimension of "<SectionName2>" on Home Page
    And I validate the alignment and relative dimension of "<SectionName3>" on Home Page
    And I validate the alignment and relative dimension of "<SectionName4>" on Home Page
   	And I validate the alignment and relative dimension of "<SectionName5>" on Home Page
   	
   Examples:
    |SectionName1	|SectionName2   	|SectionName3	|SectionName4	|SectionName5	|
    |Top Stories 	|Viewpoints			|ST Food				|Web Specials		|Entertainment	|
    
   Scenario: Bookmark Tests
   	Then I open the last article on Home Page 
   	And capture the dimension of elements on Article Detail Page
   	Then I bookmark the article in view
   	And Keep Scrolling to navigate and bookmark the articles on Home Page
   
   
   
    #Then I validate the alignment and title of each articles in Home Page 
    #Then I goto STNow Tab in Home Page View
    #When I open each article in STNow
    #Then I validate all articles are opened with respective titles and publish dates
    #When I navigate till end of STNow section
    #Then I open Recap hyperlink
    #And Ensure it opens the previous day articles
    #Then Click on Back button
    #And Goto Home Tab in Home Page View
    
    