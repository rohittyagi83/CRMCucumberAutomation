package utilityPackage;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.TestContext;
import managers.WebDriverManager;

public class CommonMethods {
	
	 
	TestContext testContext;
	WebDriverManager webDriverManager;

	public CommonMethods(TestContext context) {
		webDriverManager = context.getWebDriverManager();
	}
	
	
	public void selectByVisibleText(WebElement locator, String value) {
		//WebElement webElement = driver.findElement(locator);
		Select dropdown = new Select (locator);		 
		dropdown.selectByVisibleText(value);
		webDriverManager.waitingToLoad(2);
	}
	
	public void selectByIndex(WebElement locator, int index) {
//		WebElement webElement = driver.findElement(locator);
		Select dropdown = new Select (locator);		 
		dropdown.selectByIndex(index);
		webDriverManager.waitingToLoad(2);
	}
	

    
    public void clickOnTab(WebElement locator) {
    	locator.click();
    }
    

	public int getRandomDigits(int n) {
		int m = (int) Math.pow(10, n - 1);
		return m + new Random().nextInt(9 * m);
	}
	

}
