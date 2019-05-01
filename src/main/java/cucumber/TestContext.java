package cucumber;

import managers.PageObjectManager;
import managers.WebDriverManager;

public class TestContext {
	private WebDriverManager webDriverManager;
	private PageObjectManager pageObjectManager;
	
//    @Parameters("BrowsersToInclude")
	public TestContext(){
		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager(WebDriverManager.getDriver());
	}
	
//	public TestContext(String browser){
//		System.out.println(browser);
//		webDriverManager = new WebDriverManager(browser);
//		pageObjectManager = new PageObjectManager(WebDriverManager.getDriver());
//	}
	
	public WebDriverManager getWebDriverManager() {
		return webDriverManager;
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager; 
	}
 
}