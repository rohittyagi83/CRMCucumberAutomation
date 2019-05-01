package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MerchantWebPage {

	WebDriver driver;
	String dbaName;

	public MerchantWebPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getURL(String url){
		this.driver.get(url);
	}
	
	public String getDbaName() {
		return dbaName;
	}

	public void setDbaName(String dbaName) {
		this.dbaName = dbaName;
	}

	@FindBy(id = "ctl00_cphDetails_txtLookup")
	public WebElement dbaNameField;

	@FindBy(id = "ctl00_cphDetails_uniProcessingOrigin_ddlCtrl")
	public WebElement processingOrigin;
	
	@FindBy(id = "ctl00_cphDetails_uctlWorkflowSelector_uniWorkflowTemplate_ddlCtrl")
	public WebElement workFlow;
	
	
	@FindBy(id = "ctl00_cphDetails_uctlWorkflowSelector_uniTaskTemplate_ddlCtrl")
	public WebElement taskFlow;
	
	
	@FindBy(id = "ctl00_cphDetails_uctlWorkflowSelector_uniStartDate_txtValue")
	public WebElement startDate;
	
//	@FindBy(id = "ctl00_cphDetails_btnSave")
	@FindBy(xpath = "*//input[@name='ctl00$cphDetails$btnSave']")
	public WebElement SaveButton;
	
	
	public void clickElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
}
