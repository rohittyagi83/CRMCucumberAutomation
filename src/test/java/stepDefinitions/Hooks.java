package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.WebDriverManager;
 
public class Hooks {
 
	TestContext testContext;
 
	public Hooks(TestContext context) {
		testContext = context;
	}
	
	@Before
	public void BeforeSteps() throws InterruptedException{
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/

	}
	
//	@Before
//    public void beforeScenarioStart(){
//        System.out.println("-----------------Start of Scenario-----------------");
//        WebDriverManager.getDriver();
//    } 
 
	@After
	public void AfterSteps() throws InterruptedException {
		testContext.getWebDriverManager().closeDriver();
	}
}