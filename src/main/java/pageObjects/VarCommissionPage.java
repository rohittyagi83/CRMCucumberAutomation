package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VarCommissionPage {

	WebDriver driver;

	public VarCommissionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[contains(text(),'VAR Commissions')]")
	public WebElement varCommssionTab;

	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_btnAddRelationship")
	public WebElement addRelationship;
	
	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_uctlRelationshipEditor_uniRelatedEntityType_ddlCtrl")
	public WebElement relatedEntityType;
	
	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_uctlRelationshipEditor_lkpRelatedEntityID_txtDisplay")
	public WebElement relatedEntity;
	
	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_uctlRelationshipEditor_btnSave")
	public WebElement saveVarCommissionEntity;
	
	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_uctlRelationshipEditor_btnGetProductOptions")
	public WebElement getProductOptions;
	
	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_uctlRelationshipEditor_uniProductID_ddlCtrl")
	public WebElement product;
	
	@FindBy(id = "ctl00_cphDetails_uctlMerchantRelations_uctlRelationshipEditor_uniProductVersionID_ddlCtrl")
	public WebElement version;
	
	@FindBy(id = "ctl00_cphDetails_uctlCommissionParticipants_btnAddChangePartnerGroup")
	public WebElement addChangePartnerGroup;
	
	@FindBy(id = "ctl00_cphDetails_uctlCommissionParticipants_ddlSelectPartnerGroup")
	public WebElement selectPartnerGroup;
	
	@FindBy(id = "ctl00_cphDetails_uctlCommissionParticipants_btnSavePartnerGroup")
	public WebElement savePartnerGroup;
	

}
