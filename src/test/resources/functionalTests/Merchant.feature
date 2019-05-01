Feature: Feature executed for Merchant creation

Scenario: Create a Merchant
Given user navigate to CRM create new merchant portal "/MerchantPages/MerchantCreation.aspx?Entity=Merchant&EntityMode=Add" page
When enter following details to create merchant
  |DBA_NAME		  				| Processing Origin|Workflow				  |
  |DBA_<random_string_SOMETHING>|US				   |Workflow - Gift Card Order|
Then enter following details on account info page:
  |Street		  				    | Zip   | Ownership                | LocationType |Processor|FrontEndProcessor|Market    | MCC | 
  |Address_<random_string_SOMETHING>|80237	|Individual/Sole Proprietor|Store Front   |Vantiv   |SecureNet Express|Restaurant|1    |
 
And navigate to People Tab
Then enter following details to add contact:
  |FirstName		  			   | LastName                       | Zip |
  |first_<random_string_SOMETHING> |last_<random_string_SOMETHING>	|80237|

And edit the contact details to set the DOB and SSN
And close the new window tab