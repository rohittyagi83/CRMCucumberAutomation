package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProcessingPage {

	WebDriver driver;

	public ProcessingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Processing")
	public WebElement processingTab;

	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlProcessing_btnOpenGetMIDForm")
	public WebElement getVantivMerchantIDButton;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlProcessing_btnMIDAssign")
	public WebElement assignAVantivMerchantIDButton;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_btnAddTerminal")
	public WebElement addTerminalButton;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlAddTerminalPopup_lblNonIPMessageToUser")
	public WebElement addTerminalPopupMessage;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlAddTerminalPopup_btnAddTerminalClose")
	public WebElement addTerminalPopupClose;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_btnRefreshTerminalList")
	public WebElement refreshTerminalList;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlAutoCreateTernimals_btnAutoCreateTerminals")
	public WebElement autoCreateTerminals;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlAutoCreateTernimals_btnAutoCreateTerminalsOK")
	public WebElement autoCreateTerminalsOK;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_gvwTerminals_ctl02_lbEdit")
	public WebElement editTerminal;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlTerminalEditor_uniCloseType_ddlCtrl")
	public WebElement selectCloseType;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlTerminalEditor_uniCloseMethod_ddlCtrl")
	public WebElement selectCloseMethod;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlTerminalEditor_uctlSecureNet_chkIsSecureNetTerminal")
	public WebElement secureNetTerminalCheckBox;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlTerminalEditor_btnSave")
	public WebElement saveTerminalEditorInfo;
	
	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlProcessing_btnActivateAllTerminals")
	public WebElement activateAllTerminals;

	@FindBy(id = "ctl00_cphDetails_ajaxTabsDetails_tpnlPrcessingTab_uctlTerminals_uctlTerminalEditor_uctlSecureNet_txtSecureNetID")
	public WebElement secureNetKey;
	
	
}
