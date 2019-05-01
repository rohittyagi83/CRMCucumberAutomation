package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountInfoPage {

	WebDriver driver;

	public AccountInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniStreet_txtValue")
	public WebElement streetAddress;

	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniZipcode_txtValue")
	public WebElement zipCode;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_btnAutoFill")
	public WebElement autoFill;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniDDLCity_ddlCtrl")
	public WebElement city;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniState_ddlCtrl")
	public WebElement state;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniCountry_ddlCtrl")
	public WebElement country;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniPhone_txtValue")
	public WebElement phone;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_uctlDBAAddress_uniFax_txtValue")
	public WebElement fax;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uctlAddressMRM_lbtnSetLegalSameAsDBA")
	public WebElement setSameAsDBAAddress;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_ddlMerchantType")
	public WebElement merchantType;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_ddlTypeOwnership")
	public WebElement typeOfOwnership;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_ddlLocationType")
	public WebElement locationType;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_ddlProcessor")
	public WebElement processor;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_ddlFrontEndVendor")
	public WebElement frontEndProcessor;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uniDDLMarket_ddlCtrl")
	public WebElement market;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_uniDDLMCC_ddlCtrl")
	public WebElement MCC;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_txtTaxID")
	public WebElement taxID;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_txtCustomerServicePhone")
	public WebElement ServicePhone;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlMerchantAccountInfo_btnSaveAcctInfo")
	public WebElement saveAccountInfo;
	

}
