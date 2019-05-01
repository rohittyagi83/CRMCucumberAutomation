package managers;
 
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.DriverType;
import enums.EnvironmentType;
 
public class WebDriverManager {
	private static WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
	public static final int RETRY_FOR_WAIT = 30;
/*	*//** The logger. *//*
	protected static CustomLogging logger = null;
	
	*//** The reporter. *//*
	protected static Report reporter = null;*/
 
	
	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}
	
//	public WebDriverManager(String BrowsersToInclude) {
////		System.out.println(BrowsersToInclude);
//		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser(BrowsersToInclude);
//		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
//	}
 
	public static WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}
	
	public static WebDriver getWebDriver() {
        return driver;
    }
 
	private static WebDriver createDriver() {
		   switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case REMOTE : driver = createRemoteDriver();
	        	break;
		   }
		   return driver;
	}
 
	private static WebDriver createRemoteDriver() {
		String hubip = "";
		URL hubURL = null;
		Platform exePlatform = Platform.valueOf(FileReaderManager.getInstance().getConfigReader().getPlatform().toUpperCase());
		String driverExefilePath = FileReaderManager.getInstance().getConfigReader().getDriverPath();
//		RemoteWebDriver driver;
		try {
			if(FileReaderManager.getInstance().getConfigReader().getParallelRun().equals("true")){
				if (! FileReaderManager.getInstance().getConfigReader().getHubIP().isEmpty()) {
					hubip = FileReaderManager.getInstance().getConfigReader().getHubIP();
					hubURL = new URL("http://" + hubip + "/wd/hub");
				}
			}
	        switch (driverType) {  
	        case FIREFOX :
				FirefoxBinary firefoxBinary = new FirefoxBinary(); 
				if(exePlatform.equals(Platform.WINDOWS)){
					System.setProperty(GECKO_DRIVER_PROPERTY, driverExefilePath + File.separator + "geckodriver.exe");
				}
				else{
					System.setProperty(GECKO_DRIVER_PROPERTY, driverExefilePath + File.separator + "geckodriver_" + exePlatform);
				}	
				FirefoxOptions ffOptions = new FirefoxOptions();
				ffOptions.setBinary(firefoxBinary);
				DesiredCapabilities firefoxCP = new DesiredCapabilities();
				firefoxCP.setPlatform(exePlatform);
				ffOptions.merge(firefoxCP);
				if (!(hubip.isEmpty())) {
					driver = new RemoteWebDriver(hubURL,DesiredCapabilities.firefox());
				} else {
					driver = new FirefoxDriver(ffOptions);
				}
		    	break;
	        case CHROME :
				ChromeOptions chOptions = new ChromeOptions();
				DesiredCapabilities chromeCP = new DesiredCapabilities();
				chromeCP.setPlatform(exePlatform);
				chOptions.addArguments("--start-maximized"); 	// Added for Bug# 1505
				chOptions.merge(chromeCP);
				if(exePlatform.equals(Platform.WINDOWS)){
					System.setProperty("webdriver.chrome.driver", driverExefilePath + File.separator + "chromedriver.exe");
				} else {
					chOptions.addArguments("--disable-gpu");
					chOptions.addArguments("--no-sandbox");
					System.setProperty("webdriver.chrome.driver", driverExefilePath + File.separator + "chromedriver_" + exePlatform);
				}
				
				if (!(hubip.isEmpty())) {
					driver = new RemoteWebDriver(hubURL, chOptions);
				} else {
					driver = new ChromeDriver(chOptions);
				}
	    		break;
	        case INTERNETEXPLORER : driver = new InternetExplorerDriver();
	    		break;
			default:
				throw new Exception("Given Browser: " + driverType
						+ " not found for launching");
	        }
	        
		}
			catch(Exception e){
				//FrameworkException fe = new FrameworkException(e.getMessage());
				//reporter.LogFail("Error Launching browser: " + fe);
				//logger.Log(LogType.FATAL, fe.toString());
				 e.printStackTrace();;
			}
		return driver;
	}
 
	private static WebDriver createLocalDriver() {
		String driverExefilePath = FileReaderManager.getInstance().getConfigReader().getDriverPath();
		try {
			switch (driverType) {
			case FIREFOX:
				System.setProperty(GECKO_DRIVER_PROPERTY, driverExefilePath + File.separator + "geckodriver.exe");
				driver = new FirefoxDriver();
				//reporter.LogFail("FIREFOX Launching browser: ");
				break;
			case CHROME:
				System.setProperty(CHROME_DRIVER_PROPERTY, driverExefilePath + File.separator + "chromedriver.exe");
				driver = new ChromeDriver();
				//reporter.LogFail("CHROME Launching browser: ");
				break;
			case INTERNETEXPLORER:
				System.setProperty(IE_DRIVER_PROPERTY, driverExefilePath + File.separator + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			default:
				throw new Exception("Given Browser: " + driverType + " not found for launching");
			}
		} catch (Exception e) {
			e.printStackTrace();
//			FrameworkException fe = new FrameworkException(e.getMessage());
//			reporter.LogFail("Error Launching browser: " + fe);
//			logger.Log(LogType.FATAL, fe.toString());
//			throw fe;
		}
 
		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),TimeUnit.SECONDS);
		return driver;
	}	
	
	public boolean waitForJSandJQueryToLoad() {

	    WebDriverWait wait = new WebDriverWait(driver, 30);

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	        	System.out.println("Request = "+ ((JavascriptExecutor)driver).executeScript("return jQuery.active"));
	          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          // no jQuery present
	        	System.out.println("no jQuery present");
	          return true;
	        }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
	        .toString().equals("complete");
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
 
	private static WebElement getLoadingIcon() {
        WebElement loadingIcon = null;
        List<WebElement> ls = driver.findElements(By.tagName("img"));
         System.out.println("loading image found = " + ls.size());
        for (WebElement l : ls) {
            if (l.getAttribute("src").contains("ProgressWheel")) {
                if (l.isDisplayed()) {
                     System.out.println("Displayed loading image fodund ... ");
                    loadingIcon = l;
                    break;
                }
            }
        }
		return loadingIcon;
    }
    
	/**
	 * Method to wait for processing image to get disappear
	 * @param sec : Number of second
	 */
    public void waitingToLoad(int sec) {
        int i = 0;
        try {
            WebElement loadingIcon = getLoadingIcon();
            if(loadingIcon != null && loadingIcon.isDisplayed()) {
                while (loadingIcon.isDisplayed()) {
                	System.out.println("Running inside While loop with interval = " + sec);
                	Thread.sleep(sec * 1000);
                    i++;
                    if(i == RETRY_FOR_WAIT){
                        break;
                    }
                    loadingIcon = getLoadingIcon();
                }
            } else {
//                LOG.debug("No Loading Icon identified. Skipping wait of {} sec", sec);
            	System.out.println("No Loading Icon identified. Skipping wait of = " +  sec);
            }
        } catch (Exception e) {
        	System.out.println("No Loading Icon identified. Skipping wait of");
        }
    }
    
    /**
     * To click on button using Java Script
     * @param element : Pass the WebElement of a locator
     */
	public void clickElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void closeDiaglogBox() {
		try {
	        Alert alert = driver.switchTo().alert();
	        String AlertText = alert.getText();
	        System.out.println(AlertText);
	        alert.accept();
	    } catch (Exception e) {
	        System.out.println("no alert");
	    }
	}
	
	public void closeDriver() throws InterruptedException {
		driver.close();
		driver = null;
		Thread.sleep(1000);
//		driver.quit();
	}
	
 
}