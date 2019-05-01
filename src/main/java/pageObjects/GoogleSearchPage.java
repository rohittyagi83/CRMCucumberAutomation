package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class GoogleSearchPage {
	WebDriver driver;
	//ConfigFileReader configFileReader;
	
	public GoogleSearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//configFileReader = new ConfigFileReader();
	}
	
	@FindBy(name = "q")
	public WebElement searchTextBox;
	
	@FindBy(xpath = "//*[@class='lsb'][@value='Google Search']")
	public WebElement searchButton;
	
	@FindBy(partialLinkText = "www.seleniumhq.org")
	public WebElement searchString;
	
	public void enterSearchString(String searchString){
		this.searchTextBox.sendKeys(searchString);
	}
	
	public void clickOnSearch(){
		this.searchButton.click();
	}
	
	public void getURL(String url){
		this.driver.get(url);
	}

}
