Feature: Selenium ORG feature file
@sanity
Scenario: Browse content for selenium
Given user navigate to "https://www.seleniumhq.org" page
When user search click on download tab
Then verify the download version section should be displayed