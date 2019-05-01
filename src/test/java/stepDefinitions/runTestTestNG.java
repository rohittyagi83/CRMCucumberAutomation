package stepDefinitions;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import managers.FileReaderManager;

@CucumberOptions(
	features = {"classpath:functionalTests/Merchant.feature"},
	plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
	//,tags={"@sanity,@Regression"}
	)

@Test
public class runTestTestNG extends AbstractTestNGCucumberTests
{
	@AfterClass
	public static void teardown() {
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows");
		Reporter.setTestRunnerOutput("Sample test runner output message");
	}
	
}
