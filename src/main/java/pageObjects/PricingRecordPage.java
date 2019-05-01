package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PricingRecordPage {

	WebDriver driver;

	public PricingRecordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@id='ctl00_cphDetails_ajaxTabsDetails_apnlPeople_tab']")
	public WebElement peopleTab;

	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_btnAdd")
	public WebElement addContact;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_txtFirstName")
	public WebElement firstName;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_txtLastName")
	public WebElement lastName;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_btnCheckForDuplicateName")
	public WebElement checkForDuplicateName;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_uctlAddress_uniZipcode_txtValue")
	public WebElement zipCode;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_cblContactRoles_10")
	public WebElement contactRoleSigner;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_cblContactRoles_9")
	public WebElement contactRolePrimaryContact;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_btnSave")
	public WebElement saveContact;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_uctlAddress_btnAutoFill")
	public WebElement autoFill;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlPeople_uctlContactList_gvwBusinessContacts_ctl02_ibtnNewWindow")
	public WebElement openContactInNewWindow;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlContactInfo_uctlContact_uniOwnBirthDate_txtValue")
	public WebElement birthDate;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlContactInfo_uctlContact_txtOwnSSN")
	public WebElement SSN;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_apnlContactInfo_uctlContact_btnApply")
	public WebElement UpdateContact;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlRaftTerminals_lblRaftTerminalHeader")
	public WebElement raftTerminalLabel;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlRaftTerminals_btnShowRaftTerminals")
	public WebElement showRaftTerminalsButton;
	

}
