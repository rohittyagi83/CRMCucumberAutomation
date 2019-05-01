package stepDefinitions;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.AmazonShoppingPage;
import pageObjects.GoogleSearchPage;
import utilityPackage.DataHelper;
 
public class amazonSteps {
	TestContext testContext;
	AmazonShoppingPage amazonShoppingPage;
	GoogleSearchPage googleSearchPage;
	public List<HashMap<String,String>> datamap;
	public List<HashMap<String,String>> datamap2;
	
	
	public amazonSteps(TestContext context) {
		System.out.println("***** amazonShoppingSteps TEST *********");
		datamap = DataHelper.data(System.getProperty("user.dir")+FileReaderManager.getInstance().getConfigReader().getExcelPath(),"Sheet1");
		datamap2 = DataHelper.data(System.getProperty("user.dir")+FileReaderManager.getInstance().getConfigReader().getExcelPath(),"Sheet2");
		testContext = context;
		amazonShoppingPage = testContext.getPageObjectManager().getAmazonShoppingPage();
	}
	

	@Given("^user navigate to \"([^\"]*)\" page")
	public void navigateToPage(String url){
		amazonShoppingPage.getURL(url);
	}
	
	@When("^user search for product \"([^\"]*)\"")
	public void search(String searchText) throws InterruptedException{
		amazonShoppingPage.enterSearchString(searchText);
		Thread.sleep(1000);
		amazonShoppingPage.clickOnSearch();
	}
	
	@Then("^search product should be displayed")
	public void verifySearchProduct() {
		Boolean flag = amazonShoppingPage.searchString.isDisplayed();
		Assert.assertTrue(flag, "Searched product does not found.");
	}
	
	@When("^user clicks on product")
	public void clickOnProduct() throws InterruptedException{
		amazonShoppingPage.clickOnProduct();
		Thread.sleep(2000);
	}
	
	@When("^user click on addToCart")
	public void addToCart() throws InterruptedException{
		amazonShoppingPage.addProductToCart();
		Thread.sleep(2000);
	}
	
	@Then("^addToCart button should be displayed")
	public void verifyaddToCartButton() {
		Set<String> list = testContext.getWebDriverManager().getDriver().getWindowHandles();
		Iterator<String> it = list.iterator();
		String parent = it.next();
		String child = it.next();		
		testContext.getWebDriverManager().getDriver().switchTo().window(child);
		Boolean flag = amazonShoppingPage.addProductToCart.isDisplayed();
		Assert.assertTrue(flag, "addToCart button does not found.");
	}
	
	@Then("^verify product added to Cart")
	public void verifyAddedProduct() throws InterruptedException{
		Boolean flag = amazonShoppingPage.productAddedToCart.isDisplayed();
		Assert.assertTrue(flag, "Product does not added to cart.");
	}
	
	@Given("^create an account with excel row \"([^\"]*)\" dataset-1")
	public void createAccount(String arg1) throws InterruptedException{
		Thread.sleep(2000);
		testContext.getWebDriverManager().waitForJSandJQueryToLoad();
		Actions builder = new Actions(testContext.getWebDriverManager().getDriver());
		builder.moveToElement(amazonShoppingPage.SignInTextHover).build().perform();
		//amazonShoppingPage.clickOnRegisterAccountLink();
		click(amazonShoppingPage.RegisterAccount);
		Thread.sleep(5000);
		int index = Integer.parseInt(arg1)-1;
		amazonShoppingPage.enterCustomerName(datamap.get(index).get("Name"));
		amazonShoppingPage.enterMobileNumber(datamap.get(index).get("mobileNumber"));
		amazonShoppingPage.enterEmailAddress(datamap.get(index).get("email"));
		amazonShoppingPage.enterPassword(datamap.get(index).get("password"));
		amazonShoppingPage.clickOnContinue();
		Thread.sleep(3000);
	}
	
	@Then("^Error message must be displayed from excel row \"([^\"]*)\" dataset-1")
	public void verifyErrorMsg(String args) throws InterruptedException{
		int index = Integer.parseInt(args)-1;
//		System.out.println("Name = "+ datamap.get(index).get("Name"));
//		System.out.println("ErrorMessage = "+ datamap.get(index).get("ErrorMessage"));
		Boolean flag = amazonShoppingPage.verify(datamap.get(index).get("ErrorMessage"));
//		System.out.println("FLAG = "+ flag);
		Assert.assertTrue(flag, "Error message does not match.");
	}
	
	@Given("^create an account with excel row \"([^\"]*)\" dataset-2")
	public void createAccount2(String arg1) throws InterruptedException{
		testContext.getWebDriverManager().waitForJSandJQueryToLoad();
		Actions builder = new Actions(testContext.getWebDriverManager().getDriver());
		builder.moveToElement(amazonShoppingPage.SignInTextHover).build().perform();
		//amazonShoppingPage.clickOnRegisterAccountLink();
		
		click(amazonShoppingPage.RegisterAccount);
		Thread.sleep(1000);
		int index = Integer.parseInt(arg1)-1;
		amazonShoppingPage.enterCustomerName(datamap2.get(index).get("Name"));
		amazonShoppingPage.enterMobileNumber(datamap2.get(index).get("mobileNumber"));
		amazonShoppingPage.enterEmailAddress(datamap2.get(index).get("email"));
		amazonShoppingPage.enterPassword(datamap2.get(index).get("password"));
		amazonShoppingPage.clickOnContinue();
		Thread.sleep(2000);
	}
	
	@Then("^Error message must be displayed from excel row \"([^\"]*)\" dataset-2")
	public void verifyErrorMsg2(String args) throws InterruptedException{
		int index = Integer.parseInt(args)-1;
//		System.out.println("Name = "+ datamap.get(index).get("Name"));
//		System.out.println("ErrorMessage = "+ datamap.get(index).get("ErrorMessage"));
		Boolean flag = amazonShoppingPage.verify(datamap2.get(index).get("ErrorMessage"));
//		System.out.println("FLAG = "+ flag);
		Assert.assertTrue(flag, "Error message does not match.");
	}
	
 public void click(WebElement element){
	 JavascriptExecutor executor = (JavascriptExecutor)testContext.getWebDriverManager().getDriver();
	 executor.executeScript("arguments[0].click();", element);
 }
}