package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnderwritingPage {

	WebDriver driver;

	public UnderwritingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "ctl00_cphSummary_uctlHeader_lnkPricingConsole")
	public WebElement pricingRecordLink;

	@FindBy(id = "PricingPanelModel_EntitySurchargeModel_SurchargeShortDescription")
	public WebElement surcharge;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlFinancialInfo_uctlCreditActions_btnSave")
	public WebElement saveCreditActions;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlFinancialInfo_uctlBankAccountEditorGrid_RadGrid1_ctl00_ctl02_ctl03_txtFinancialInstitution")
	public WebElement financialInstitution;
	
	@FindBy(id = "txtRouting")
	public WebElement routing;
	
	@FindBy(id = "txtRoutingEdit")
	public WebElement verifyRouting;
	
	@FindBy(id = "txtAccountNumber")
	public WebElement acccountNumber;
	
	@FindBy(id = "txtAcctNumValid")
	public WebElement verifyAccountNumber;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlFinancialInfo_uctlBankAccountEditorGrid_RadGrid1_ctl00_ctl02_ctl03_PerformInsertButton")
	public WebElement SaveBankDetails;
	

}
