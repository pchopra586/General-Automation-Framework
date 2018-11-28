Feature: Validate Terms of use page
  Verify elements of Terms of use Page.

Scenario: Verify functionality of decline agreement button
    Given App is freshly installed and launched
    When decline and accept buttons are visible
    Then I validate the functionality of cancel agreement button
    
