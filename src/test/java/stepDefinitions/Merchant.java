package stepDefinitions;
 
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import cucumber.TestContext;
import cucumber.api.DataTable;
import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import managers.FileReaderManager;
import managers.WebDriverManager;
import pageObjects.AccountInfoPage;
import pageObjects.MerchantWebPage;
import pageObjects.PeoplePage;
import utilityPackage.CommonMethods;
import utilityPackage.RandomStringTransformer;
 
public class Merchant {
	TestContext testContext;
	MerchantWebPage merchantWebPage;
	AccountInfoPage accountInfoPage;
	PeoplePage peoplePage;
	WebDriverManager webDriverManager;
	CommonMethods commonMethods;
	
	
	public Merchant(TestContext context) {
		System.out.println("***** SeleniumSteps TEST *********");
		testContext = context;
		merchantWebPage = testContext.getPageObjectManager().getMerchantWebPage();
		accountInfoPage = testContext.getPageObjectManager().getAccountInfoPage();
		peoplePage = testContext.getPageObjectManager().getPeoplePage();
		webDriverManager = testContext.getWebDriverManager();
		commonMethods = new CommonMethods(testContext);
	}
	
	@Given("^user navigate to CRM create new merchant portal \"([^\"]*)\" page")
	public void navigateToPage(String urlPath){
		String appURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
		merchantWebPage.getURL(appURL + urlPath);
	}
	
	/*
	 * @When("^enter following details to create merchant$") public void
	 * search(DataTable dataTable) throws InterruptedException{ List<Map<String,
	 * String>> list = dataTable.asMaps(String.class, String.class);
	 * 
	 * System.out.println(list.get(0).get("DBA_NAME"));
	 * System.out.println(list.get(0).get("Processing Origin"));
	 * System.out.println(list.get(0).get("Workflow"));
	 * 
	 * }
	 */
	
	@When("^enter following details to create merchant")
	public void addMerchant(@Transform(RandomStringTransformer.class) DataTable fieldValues) throws InterruptedException {
		fieldValues = RandomStringTransformer.INSTANCE.transform(fieldValues);
	    final List<DataTableRow> rows = fieldValues.getGherkinRows();
	    //final List<String> columns = rows.get(0).getCells();
		if (!rows.isEmpty()) {
			// Remove the column headers.
			for (DataTableRow row : rows.subList(1, rows.size())) {
				List<String> rowValues = row.getCells();
				System.out.println("DBA NAME = " + rowValues.get(0));
				merchantWebPage.setDbaName(rowValues.get(0));
				merchantWebPage.dbaNameField.sendKeys(rowValues.get(0));
				commonMethods.selectByVisibleText(merchantWebPage.processingOrigin, rowValues.get(1));
				commonMethods.selectByVisibleText(merchantWebPage.workFlow, rowValues.get(2));
//				commonMethods.selectByVisibleText(merchantWebPage.taskFlow, "Order Issue");
//				webDriverManager.waitingToLoad(2);
			}
			/*
			 * for (int i = 0; i < columns.size(); i++) { if
			 * (rowValues.get(i).contains("DBA_")) {
			 * 
			 * //Store DBA NAme for future purpose
			 * merchantWebPage.setDbaName(rowValues.get(i));
			 * merchantWebPage.dbaNameField.sendKeys(rowValues.get(i)); }
			 * System.out.println(rowValues.get(i));
			 * merchantWebPage.processingOrigin.sendKeys(rowValues.get(i));
			 * merchantWebPage.workFlow.sendKeys(rowValues.get(i)); }
			 */
//			webDriverManager.waitingToLoad(10);
//			merchantWebPage.startDate.click();
//			Thread.sleep(10000);
//			merchantWebPage.clickElement(merchantWebPage.SaveButton);
			merchantWebPage.SaveButton.click();
			webDriverManager.waitingToLoad(2);
		}  	    
	
	}
	
	
	@Then ("^enter following details on account info page:")
	public void enterAccountInfo(@Transform(RandomStringTransformer.class) DataTable fieldValues) {
		fieldValues = RandomStringTransformer.INSTANCE.transform(fieldValues);
	    final List<DataTableRow> rows = fieldValues.getGherkinRows();
		if (!rows.isEmpty()) {
			//Generate 10 digit random number for phone number
			String phoneNumber = String.valueOf(commonMethods.getRandomDigits(10));
			// Remove the column headers.
			for (DataTableRow row : rows.subList(1, rows.size())) {
				List<String> rowValues = row.getCells();
				accountInfoPage.streetAddress.sendKeys(rowValues.get(0));
				accountInfoPage.zipCode.sendKeys(rowValues.get(1));
				accountInfoPage.autoFill.click();
				//Wait for loading image to disappear
				webDriverManager.waitingToLoad(1);
				accountInfoPage.phone.sendKeys(phoneNumber);
				accountInfoPage.setSameAsDBAAddress.click();
				webDriverManager.waitingToLoad(1);
				commonMethods.selectByVisibleText(accountInfoPage.typeOfOwnership, rowValues.get(2));
				commonMethods.selectByVisibleText(accountInfoPage.locationType, rowValues.get(3));
				commonMethods.selectByVisibleText(accountInfoPage.processor, rowValues.get(4));
				//Wait for loading image to disappear
				commonMethods.selectByVisibleText(accountInfoPage.frontEndProcessor, rowValues.get(5));
				commonMethods.selectByVisibleText(accountInfoPage.market, rowValues.get(6));
				commonMethods.selectByIndex(accountInfoPage.MCC, Integer.parseInt(rowValues.get(7)));
				
				//Generate 9 digit random number for Tax ID
				accountInfoPage.taxID.sendKeys(String.valueOf(commonMethods.getRandomDigits(9)));
				
				accountInfoPage.ServicePhone.sendKeys(phoneNumber);
			}
			accountInfoPage.saveAccountInfo.click();
			webDriverManager.waitingToLoad(2);
		}
	}
	
	
	
	@And("^navigate to People Tab")
	public void navigateToPeopleTab() {
		commonMethods.clickOnTab(peoplePage.clickPeopleTab);
		webDriverManager.waitingToLoad(2);
	}
	
	@Then("^enter following details to add contact:")
	public void addContactDetails(@Transform(RandomStringTransformer.class) DataTable fieldValues) {
		fieldValues = RandomStringTransformer.INSTANCE.transform(fieldValues);
	    final List<DataTableRow> rows = fieldValues.getGherkinRows();
		if (!rows.isEmpty()) {
			peoplePage.addContact.click();
			// Remove the column headers.
			for (DataTableRow row : rows.subList(1, rows.size())) {
				List<String> rowValues = row.getCells();
				peoplePage.firstName.sendKeys(rowValues.get(0));
				peoplePage.lastName.sendKeys(rowValues.get(1));
				peoplePage.checkForDuplicateName.click();
				//Wait for loading image to disappear
				webDriverManager.waitingToLoad(1);
				peoplePage.zipCode.sendKeys(rowValues.get(2));
				peoplePage.autoFill.click();
				webDriverManager.waitingToLoad(1);
				
				peoplePage.selectAndSaveContactRules();
				
//				//Select the Contact Roles
//				peoplePage.contactRolePrimaryContact.click();
//				peoplePage.contactRoleSigner.click();
//				
//				//Save the contact details
//				peoplePage.saveContact.click();
//				webDriverManager.waitingToLoad(1);
				
//				commonMethods.selectByVisibleText(accountInfoPage.typeOfOwnership, rowValues.get(2));
//				commonMethods.selectByVisibleText(accountInfoPage.locationType, rowValues.get(3));
//				commonMethods.selectByVisibleText(accountInfoPage.processor, rowValues.get(4));
//				//Wait for loading image to disappear
//				webDriverManager.waitingToLoad(1);
//				commonMethods.selectByVisibleText(accountInfoPage.frontEndProcessor, rowValues.get(5));
//				commonMethods.selectByVisibleText(accountInfoPage.market, rowValues.get(6));
//				webDriverManager.waitingToLoad(1);
//				commonMethods.selectByIndex(accountInfoPage.MCC, Integer.parseInt(rowValues.get(7)));
//				
//				//Generate 9 digit random number for Tax ID
//				accountInfoPage.taxID.sendKeys(String.valueOf(commonMethods.getRandomDigits(9)));
//				
//				accountInfoPage.ServicePhone.sendKeys(phoneNumber);
			}
//			accountInfoPage.saveAccountInfo.click();
//			webDriverManager.waitingToLoad(2);
		}
	}
	
	@And("^edit the contact details to set the DOB and SSN")
	public void editContactDetails() {
		closeTab();
//		peoplePage.openContactInNewWindow.click();
//		webDriverManager.waitingToLoad(1);
//		peoplePage.birthDate.sendKeys("1/1/1999");
//		peoplePage.SSN.sendKeys(String.valueOf(commonMethods.getRandomDigits(9)));
//		peoplePage.UpdateContact.click();
	}
	
	public void closeTab() {
		//Storing current window's handle
		String strFirstWindowHandle = WebDriverManager.getWebDriver().getWindowHandle();
		//Clicking on a link that opens a new window
		peoplePage.openContactInNewWindow.click();
		//Storing the collection of all opened windows (in our case it would be 2)
		Set<String> setWindowHandles = WebDriverManager.getWebDriver().getWindowHandles();
		//Iterating over all windows handles
		for(String strWindowHandle: setWindowHandles){
			//If the window handle is not same as the one stored before opening up second window, it is the new window
			if(!strWindowHandle.equals(strFirstWindowHandle)){
				//Switch to the new window
				WebDriverManager.getWebDriver().switchTo().window(strWindowHandle);
				//Print window title
				System.out.println(WebDriverManager.getWebDriver().getTitle());
		        //Close this browser window
				WebDriverManager.getWebDriver().close();
				//Exit from loop
				break;
			}
		}
		//Switch back to the parent browser window
		WebDriverManager.getWebDriver().switchTo().window(strFirstWindowHandle);
		//Print window title
		System.out.println(WebDriverManager.getWebDriver().getTitle());
	}
	
}