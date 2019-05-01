package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.AccountInfoPage;
import pageObjects.AmazonShoppingPage;
import pageObjects.GoogleSearchPage;
import pageObjects.MerchantWebPage;
import pageObjects.PeoplePage;
import pageObjects.PricingRecordPage;
import pageObjects.ProcessingPage;
import pageObjects.SeleniumWebPage;
import pageObjects.UnderwritingPage;
import pageObjects.VarCommissionPage;

public class PageObjectManager {
	private WebDriver driver;
	
	
	private GoogleSearchPage googleSearch;
	private AmazonShoppingPage amazonShoppingPage;
	private SeleniumWebPage seleniumWebPage;
	private MerchantWebPage merchantWebPage;
	private AccountInfoPage accountInfoPage;
	private PeoplePage peoplePage;
	private ProcessingPage processingPage;
	private VarCommissionPage varCommissionPage;
	private UnderwritingPage underwritingPage;
	private PricingRecordPage pricingRecordPage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public GoogleSearchPage getGoogleSearchPage(){		 
		return (googleSearch == null) ? googleSearch = new GoogleSearchPage(driver) : googleSearch;
 
	}
	
	public AmazonShoppingPage getAmazonShoppingPage(){		 
		return (amazonShoppingPage == null) ? amazonShoppingPage = new AmazonShoppingPage(driver) : amazonShoppingPage;
 
	}
	
	public SeleniumWebPage getSeleniumWebPage(){		 
		return (seleniumWebPage == null) ? seleniumWebPage = new SeleniumWebPage(driver) : seleniumWebPage;
 
	}
	
	public MerchantWebPage getMerchantWebPage(){		 
		return (merchantWebPage == null) ? merchantWebPage = new MerchantWebPage(driver) : merchantWebPage;
 
	}
	
	public AccountInfoPage getAccountInfoPage(){		 
		return (accountInfoPage == null) ? accountInfoPage = new AccountInfoPage(driver) : accountInfoPage;
 
	}
	
	public PeoplePage getPeoplePage(){		 
		return (peoplePage == null) ? peoplePage = new PeoplePage(driver) : peoplePage;
 
	}
	
	public ProcessingPage getProcessingPage(){		 
		return (processingPage == null) ? processingPage = new ProcessingPage(driver) : processingPage;
 
	}
	
	public VarCommissionPage getVarCommissionPage(){		 
		return (varCommissionPage == null) ? varCommissionPage = new VarCommissionPage(driver) : varCommissionPage;
 
	}
	
	public UnderwritingPage getUnderwritingPage(){		 
		return (underwritingPage == null) ? underwritingPage = new UnderwritingPage(driver) : underwritingPage;
 
	}
	
	public PricingRecordPage getPricingRecordPage(){		 
		return (pricingRecordPage == null) ? pricingRecordPage = new PricingRecordPage(driver) : pricingRecordPage;
 
	}
}
