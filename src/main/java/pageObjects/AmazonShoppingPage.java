package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonShoppingPage {
	WebDriver driver;
	
	public AmazonShoppingPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	boolean flag = false;
	
	@FindBy(id = "twotabsearchtextbox")
	public WebElement amzonSearchTextBox;
	
	@FindBy(xpath = "//div[contains(@class,'nav-search-submit')]//input")
	public WebElement searchButton;
	
	@FindBy(xpath = "//*[text()='Apple iPhone 8 (Space Grey, 64GB)']")
	public WebElement searchString;
	
	@FindBy(xpath = "//*[@id='add-to-cart-button']")
	public WebElement addProductToCart;
	
	@FindBy(xpath = "//*[@id='huc-v2-order-row-messages']//h1")
	public WebElement productAddedToCart;
	
	@FindBy(xpath = "//*[@id='nav-flyout-ya-newCust']//a[1]")
	public WebElement RegisterAccount;
	
	@FindBy(name = "customerName")
	public WebElement customerName;
	
	@FindBy(id = "ap_phone_number")
	public WebElement mobileNumber;
	
	@FindBy(name = "secondaryLoginClaim")
	public WebElement emailAddress;
	
	@FindBy(name = "password")
	public WebElement password;
	
	@FindBy(id = "continue")
	public WebElement continueButton;
	
	@FindBy(xpath = "//*[contains(text(),'Hello. Sign in')]")
	public WebElement SignInTextHover;
	
	@FindBy(xpath = "//*[@id='auth-error-message-box']")
	public WebElement errorVerification;
	
	public void enterSearchString(String searchString){
		amzonSearchTextBox.sendKeys(searchString);
	}
	
	public void clickOnSearch(){
		this.searchButton.click();
	}
	
	public void getURL(String url){
		this.driver.get(url);
	}
	
	public void clickOnProduct(){
		this.searchString.click();
	}
	
	public void addProductToCart(){
		this.addProductToCart.click();
	}
	
	public void clickOnRegisterAccountLink(){
		this.RegisterAccount.click();
	}
	
	public void enterCustomerName(String name){
		this.customerName.sendKeys(name);
	}
	
	public void enterMobileNumber(String mobile){
		this.mobileNumber.sendKeys(mobile);
	}
	
	public void enterEmailAddress(String email){
		this.emailAddress.sendKeys(email);
	}
	
	public void enterPassword(String userPassword){
		this.password.sendKeys(userPassword);
	}
	
	public void clickOnContinue(){
		this.continueButton.click();
	}

	public Boolean verify(String errorMsg){
		return flag = this.errorVerification.getText().contains(errorMsg);
	}
}
