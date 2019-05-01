package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumWebPage {

	WebDriver driver;

	public SeleniumWebPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='menu_download']/a")
	public WebElement downloadTab;

	@FindBy(xpath = "//*[@id='mainContent']")
	public WebElement mainContent;
}
