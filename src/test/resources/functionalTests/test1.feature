Feature: Feature executed for Google Search for test1 file

@Regression
Scenario: Google search-1
Given user navigate to google search page
When user search for text "www.seleniumhq.org"
Then search content "www.seleniumhq.org" should be displayed

@sanity
Scenario: Google search-99
Given user navigate to google search page
When user search for text "www.seleniumhq.org"
Then search content "www.seleniumhq.org" should be displayed