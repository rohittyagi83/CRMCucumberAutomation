package stepDefinitions;
 
import java.util.HashMap;
import java.util.List;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.GoogleSearchPage;
import utilityPackage.DataHelper;
 
public class GoogleSearchSteps {
//	WebDriver driver;
//	PageObjectManager pageObjectManager;
//	GoogleSearch googleSearch;
//	WebDriverManager webDriverManager;
	TestContext testContext;
	GoogleSearchPage googleSearchPage;	
	public List<HashMap<String,String>> datamap;
	
	public GoogleSearchSteps(TestContext context) {
		System.out.println("PRINT GoogleSearchSteps");
		datamap = DataHelper.data(System.getProperty("user.dir")+FileReaderManager.getInstance().getConfigReader().getExcelPath(),"Sheet1");
		testContext = context;
		googleSearchPage = testContext.getPageObjectManager().getGoogleSearchPage();
	}
	
	@Then("^I contact the customer service with excel row \"(.*?)\" dataset$")
    public void i_contact_the_customer_service_with_excel_row_dataset(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        System.out.println("Printing current data set...");
        for(HashMap h:datamap)
        {
            System.out.println(h.keySet());
            System.out.println(h.values());
        }
    }

	@Given("^user navigate to google search page")
	public void navigateToGoogleSearch(){
//		webDriverManager = new WebDriverManager();
//		driver = webDriverManager.getDriver();
//		pageObjectManager = new PageObjectManager(driver);
//		googleSearch = pageObjectManager.getGoogleSearchPage();
		googleSearchPage.getURL(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		//reporter.LogInfo("Browse the URL");
		//.addStepLog("Browse the URL");
	}
	
	@When("^user search for text \"([^\"]*)\"")
	public void search(String searchText) throws InterruptedException{
		googleSearchPage.enterSearchString(searchText);
		//Reporter.addStepLog("Enter the search string");
		Thread.sleep(3000);
		googleSearchPage.clickOnSearch();
		//Reporter.addStepLog("Click on Search button");
		//reporter.LogInfo("Click on Search button");
	}
	
	@Then("^search content \"([^\"]*)\" should be displayed")
	public void verifySearchContent(String searchText) {
		googleSearchPage.searchString.isDisplayed();
		//Reporter.addStepLog("Validate the search content is displayed");
		//reporter.LogInfo("Validate the search content is displayed");
	}
	
}