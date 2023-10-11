package managers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import enums.DriverType;
import enums.EnvironmentType;


public class WebDriverManager {
//	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	static String driverExefilePath = FileReaderManager.getInstance().getConfigReader().getDriverPath();
//	private static WebDriver driver;
	private static DriverType driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
	private static EnvironmentType environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
	public static final int RETRY_FOR_WAIT = 60;
	
	private static final User32 user32 = User32.INSTANCE;
	private static final int SWP_NOSIZE = 0x0001;
	private static final int SWP_NOMOVE = 0x0002;
	private static HWND browserWindowHandle;
	private static Logger log = LogManager.getLogger(WebDriverManager.class.getName());

//	final Logger logger = Log.getLogData(Log.class.getName());

	/*	*//** The logger. */

	/*
	 * protected static CustomLogging logger = null;
	 * 
	 *//** The reporter. *//*
							 * protected static Report reporter = null;
							 */

	/*
	 * public WebDriverManager() { driverType =
	 * FileReaderManager.getInstance().getConfigReader().getBrowser();
	 * environmentType =
	 * FileReaderManager.getInstance().getConfigReader().getEnvironment(); }
	 */

	public static WebDriver getWebDriver() {
//        System.out.println("Getting instance of web driver");
        return driver.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
    	System.out.println("Setting the driver");
    	driver.set(webDriver);
    }
    
    static WebDriver createDriver() {
    	WebDriver driver = null;
        switch (environmentType) {     
             case LOCAL : driver = createLocalDriver();
              break;
             case REMOTE : driver = createRemoteDriver();
              break;
        }
        return driver;
     }
    
		public static WebDriver createLocalDriver() {
			WebDriver driver = null;
			if (FileReaderManager.getInstance().getConfigReader().runTestOnCorpLaptop().equalsIgnoreCase("true")) {
				System.setProperty(CHROME_DRIVER_PROPERTY, driverExefilePath + File.separator + "chromedriver.exe");
			} else {
				System.setProperty(CHROME_DRIVER_PROPERTY, driverExefilePath + File.separator + "chromedriver.exe");
			}

			String downloadFilepath = System.getProperty("user.dir") + File.separator + "target";
			log.info("download.default_directory : " + downloadFilepath);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);

			ChromeOptions options = new ChromeOptions();
			//This is deprecated
//			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-backgrounding-occluded-windows");
			
			//This will handle the alert with latest chrome drivers
			options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			
			if (FileReaderManager.getInstance().getConfigReader().runTestOnCorpLaptop().equalsIgnoreCase("true")) {
				options.addExtensions(new File("configs//Multipass.crx"));
			}
			options.setExperimentalOption("prefs", chromePrefs);
			if (FileReaderManager.getInstance().getConfigReader().runTestInHeadless().equalsIgnoreCase("true")) {
				// Set the setHeadless is equal to true which will run test in Headless mode
				options.setHeadless(true);
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				options.addArguments("--proxy-server=http://proxy.nonprod.nb01.local:8080");
				options.addArguments("--proxy-bypass-list=*.local");
				options.addArguments("--ignore-certificate-errors");
			}
			driver = new ChromeDriver(options);
			if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
				driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(
					FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
			setWindowFocus();
			if (FileReaderManager.getInstance().getConfigReader().runTestOnCorpLaptop().equalsIgnoreCase("true")) {
				setCredentials(driver);
			}
			return driver;

		}
    
	public static void setWindowFocus() {
		browserWindowHandle = user32.GetForegroundWindow();
	}
    
	static WebDriver createRemoteDriver() {
		WebDriver driver = null;
		switch (driverType) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty(CHROME_DRIVER_PROPERTY, driverExefilePath + File.separator + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-gpu");
			if (FileReaderManager.getInstance().getConfigReader().runTestInHeadless().equalsIgnoreCase("true")) {
				//Set the setHeadless is equal to true which will run test in Headless mode
				options.setHeadless(true);
			}
			//DesiredCapabilities dc = DesiredCapabilities.chrome();
			//dc.setBrowserName("chrome");
			//dc.setCapability("useAutomationExtension", false);
//			dc.setPlatform(org.openqa.selenium.Platform.VISTA); 
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case INTERNETEXPLORER:
			driver = new InternetExplorerDriver();
			break;
		}
		
		
//		driver = new ChromeDriver();
		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),
				TimeUnit.SECONDS);
		return driver;
    }
    
//    static WebDriver createInstance() {
//    	WebDriver driver = null;
//		System.setProperty("webdriver.chrome.driver",
//				driverExefilePath + File.separator + "chromedriver.exe");
//		
//		DesiredCapabilities dc = DesiredCapabilities.chrome();
//		dc.setBrowserName("chrome");
////		dc.setPlatform(org.openqa.selenium.Platform.VISTA); 
//		try {
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
////		driver = new ChromeDriver();
//		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),
//				TimeUnit.SECONDS);
//		return driver;
//    }
    
    public static void clickOKonAlertPopUp() {
		try {
			getWebDriver().switchTo().alert().accept();
		}catch (Exception ex) {
		}
		
	}

	public static boolean waitForJSandJQueryToLoad() {

		WebDriverWait wait = new WebDriverWait(getWebDriver(), 90);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					System.out.println(
							"Request = " + ((JavascriptExecutor) driver).executeScript("return jQuery.active"));
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					System.out.println("no jQuery present");
					return true;
				}
			}
		};

		// wait for Java script to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					System.out.println(
							"Request = " + ((JavascriptExecutor) driver).executeScript("return document.readyState").toString());
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
				} catch (Exception e) {
					// no jQuery present
					System.out.println("no jQuery present");
					return true;
				}
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	private static WebElement getLoadingIcon() {
		WebElement loadingIcon = null;
//		WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> ls = getWebDriver().findElements(By.tagName("img"));
//        System.out.println("loading image found = " + ls.size());
		for (WebElement l : ls) {
			if (l.getAttribute("src").contains("ProgressWheel") || l.getAttribute("src").contains("loader")
					|| l.getAttribute("src").contains("default") || l.getAttribute("src").contains("ajax-loader")) {
				if (l.isDisplayed()) {
					loadingIcon = l;
					WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
					break;
				}
			}
		}
		return loadingIcon;
	}
	
	private static WebElement getPartnerBoardingLoadingIcon() {
		WebElement loadingIcon = null;
		List<WebElement> ls = getWebDriver().findElements(By.id("divBoardingStatus"));
		for (WebElement l : ls) {
			if (l.findElement(By.id("hubCountdownTimer")) != null) {
				System.out.println("####### "+ l.isDisplayed());
				if (l.isDisplayed()) {
					loadingIcon = l;
					break;
				}
			}
		}
		return loadingIcon;
	}

	/**
	 * Method to wait for processing image to get disappear
	 * 
	 * @param sec : Number of second
	 */
	public void waitingToLoad(int sec) {
		int i = 0;
		try {

			WebElement loadingIcon = getLoadingIcon();
			if (loadingIcon != null && loadingIcon.isDisplayed()) {
				while (loadingIcon.isDisplayed()) {
//                	System.out.println("Running inside While loop with interval = " + sec);
					Thread.sleep(sec * 1000);
					i++;
					if (i == RETRY_FOR_WAIT) {
						break;
					}
					loadingIcon = getLoadingIcon();
				}
			} else {
//                LOG.debug("No Loading Icon identified. Skipping wait of {} sec", sec);
//            	System.out.println("No Loading Icon identified. Skipping wait of = " +  sec);
			}
		} catch (Exception e) {
			// System.out.println("No Loading Icon identified. Skipping wait");
		}
	}
	
	/**
	 * Method to wait for loading icon to disappear from Partner Boarding
	 * @param sec
	 */
	public void waitingToLoadPartnerBoadring(int sec) {
		int i = 0;
		try {
			WebElement loadingIcon = getPartnerBoardingLoadingIcon();
			if (loadingIcon != null && loadingIcon.isDisplayed()) {
				while (loadingIcon.isDisplayed()) {
//                	System.out.println("Running inside While loop with interval = " + sec);
					Thread.sleep(sec * 1000);
					i++;
					if (i == RETRY_FOR_WAIT) {
						break;
					}
					loadingIcon = getPartnerBoardingLoadingIcon();
				}
			} else {
//                LOG.debug("No Loading Icon identified. Skipping wait of {} sec", sec);
//            	System.out.println("No Loading Icon identified. Skipping wait of = " +  sec);
			}
		} catch (Exception e) {
			// System.out.println("No Loading Icon identified. Skipping wait");
		}
	}


	
	/**
	 * To click on button using Java Script
	 * 
	 * @param element : Pass the WebElement of a locator
	 */
	public void clickElementUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		executor.executeScript("arguments[0].click();", element);
		waitingToLoad(2);
	}
	
	public void enterTextUsingJavaScript(WebElement elementID, String value) {
		JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		executor.executeScript("arguments[0].value='"+value+"'", elementID);
	}
	
	public void clearTextUsingJavaScript(WebElement elementID) {
		JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		executor.executeScript("arguments[0].value = '';", elementID);
	}

	/**
	 * Close the instance of web driver
	 * 
	 * @throws InterruptedException
	 */
	public void closeDriver() throws InterruptedException {
		// driver = null;
		Thread.sleep(2000);
//		getWebDriver().quit();
//		Thread.sleep(3000);
//		driver = null;
	}

	/**
	 * An expectation for checking that an element is either invisible or not
	 * present on the DOM.
	 *
	 * @param locator used to find the element
	 */
	public static void absenceOfElementLocated(final WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), 30);

		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return false;
				} catch (NoSuchElementException e) {
					return true;
				} catch (StaleElementReferenceException f) {
					return true;
				}
			}
		};
		wait.until(elementIsDisplayed);		
	}
	
	/**
	 * use waitForElementToAppear to wait until the element is visible in the DOM
	 * It waits max for two minutes
	 */
	public void waitForElementToAppear(WebElement element) {
	new WebDriverWait(WebDriverManager.getWebDriver(), 120).until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * use waitForElementToDisappear to wait until the element is invisible in the DOM
	 * It waits max for two minutes
	 */
	public void waitForElementToDisappear(WebElement element) {
			new WebDriverWait(WebDriverManager.getWebDriver(), 120).until(ExpectedConditions.invisibilityOf(element));
		
		}
	
	public void waitForElementToBeClickable(WebElement element) {
		new WebDriverWait(WebDriverManager.getWebDriver(), 120).until(ExpectedConditions.elementToBeClickable(element));
	log.info("wait for element to be clickable has been over!");
	}
	

	public static void selectByVisibleText(WebElement locator, String value) {
		Select dropdown = new Select(locator);
		dropdown.selectByVisibleText(value);
	}
	
	/**
	 * This method can be used to click on button.
	 * @param element
	 * @return
	 */
	public boolean isClicked(WebElement element, int timeoutInSeconds)
	{ 
	    try {
	        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeoutInSeconds);
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	        waitingToLoad(2);
	        log.info("Element clicked --> "+ element);
	        return true;
	    } catch(Exception e){
	    	log.info("Unable to click Element --> "+ element);
	        return false;
	    }
	}
	
	
	public void waitForAlertToBePresent() {
		try {
			// Create new WebDriver wait
			WebDriverWait wait = new WebDriverWait(WebDriverManager.getWebDriver(), 180);
			// Wait for Alert to be present
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Alert message was expected but did not appear!");
			Assert.assertFalse(true, "Alert message was expected but did not appear!");
		}
	}
	
	
	public void waitForElementToBeEnabled(WebElement webElement) {		
		boolean result = false;
		int attempts = 0;
		while (attempts < 8) {
			try {
				result = webElement.isEnabled();
				if (result) {
					log.info("Element is enabled!!!");
					break;
				}
				else {
					Thread.sleep(1000);
					log.info("execution paused for 1 sec");
				}
			} catch (Exception e) {	}
			attempts++;
		}		
	}
	
	 public void waitForPageLoad(WebDriver driver, int timeOutInSeconds) {
	        ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	        wait.until(pageLoadCondition);
	        log.info("wait for Page Load method executed");
	    }
	 public static void setCredentials(WebDriver driver) {
		 	driver.get("chrome-extension://enhldmjbphoeibbpdhmjkchohnidgnah/options.html");
	        driver.findElement(By.id("url")).sendKeys(".*");
	        driver.findElement(By.id("username")).sendKeys(FileReaderManager.getInstance().getConfigReader().getCorpUserName());
	        driver.findElement(By.id("password")).sendKeys(FileReaderManager.getInstance().getConfigReader().getCorpPassword());
	        driver.findElement(By.className("credential-form-submit")).click();
	 }
	
	 public static boolean isBrowserClosed()
	 {
	     boolean isClosed = false;
	     try {
	        WebDriverManager.getWebDriver().getTitle();
	     } catch(UnreachableBrowserException ubex) {
	         isClosed = true;
	     } catch(NoSuchWindowException nswe) {
	    	 isClosed = true;
	     }
	     catch(Exception ex) {
	    	 ex.getMessage();
	     }

	     return isClosed;
	 }
}
