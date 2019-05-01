Feature: Feature executed for Google Search for test2 file

Scenario: Google search-2
Given user navigate to google search page
When user search for text "www.seleniumhq.org"
Then search content "www.seleniumhq.org" should be displayed