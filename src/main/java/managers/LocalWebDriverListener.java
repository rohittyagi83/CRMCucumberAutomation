package managers;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import cucumber.TestContext;

import java.lang.reflect.Field;


/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class LocalWebDriverListener implements IInvokedMethodListener {


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        //log.debug("BEGINNING: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("BrowsersToInclude");
            // get and set new instance of local WebDriver
            //log.info("getting driver for: " + browserName);
//            TestContext it = new TestContext(browserName);
            //WebDriver driver = LocalDriverFactory.createInstance(browserName);
            //DriverManager.setWebDriver(driver);
            //log.info("Done! Created "+ browserName + " driver!" );
        } else {
            //log.warn("Provided method is NOT a TestNG testMethod!!!");
        }
        //log.debug("END: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //log.debug("BEGINNING: org.stng.jbehave.LocalWebDriverListener.afterInvocation");
        if (method.isTestMethod()) {
            try {
//                String browser = DriverManager.getBrowserInfo();
//                // change the name of the test method that will appear in the report to one that will contain
//                // also browser name, version and OS.
//                // very handy when analysing results.
//                BaseTestMethod bm = (BaseTestMethod)testResult.getMethod();
//                Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
//                f.setAccessible(true);
//                String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - " + bm.getMethodName() + " - " + browser;
//                //log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName + "'");
//                f.set(bm, newTestName);
            } catch (Exception ex) {
                System.out.println("afterInvocation exception:\n" + ex.getMessage());
                ex.printStackTrace();
            } finally {
                // close the browser
                WebDriver driver = WebDriverManager.getWebDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
        //log.debug("END: org.stng.jbehave.LocalWebDriverListener.afterInvocation");
    }
}