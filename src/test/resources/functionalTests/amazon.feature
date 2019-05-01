Feature: Amazon search product feature file

Scenario: Search and add iphone 8
Given user navigate to "https://www.amazon.in" page
When user search for product "iphone 8"
Then search product should be displayed
When user clicks on product
Then addToCart button should be displayed
When user click on addToCart
Then verify product added to Cart

@sanity
Scenario: create amazon account with excel data
Given user navigate to "https://www.amazon.in" page
When create an account with excel row "1" dataset-1
Then Error message must be displayed from excel row "1" dataset-1

@Regression
Scenario Outline: create amazon account with scenario outline using sheet1
Given user navigate to "https://www.amazon.in" page
When create an account with excel row "<row_index>" dataset-1
Then Error message must be displayed from excel row "<row_index>" dataset-1

Examples:
	|row_index|
  |2|
  |3|
  
Scenario Outline: create amazon account with scenario outline using sheet2
Given user navigate to "https://www.amazon.in" page
When create an account with excel row "<row_indexes>" dataset-2
Then Error message must be displayed from excel row "<row_indexes>" dataset-2

Examples:
	|row_indexes|
  |2|
  |3|