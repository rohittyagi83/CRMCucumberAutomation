package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = {"C:/Users/salesvp1/eclipse-workspace/DemoCucumberProject-master/DemoCucumberProject-master/src/test/resources/functionalTests/test1.feature"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:C:/Users/salesvp1/eclipse-workspace/DemoCucumberProject-master/DemoCucumberProject-master/target/cucumber-parallel/3.html", "json:C:/Users/salesvp1/eclipse-workspace/DemoCucumberProject-master/DemoCucumberProject-master/target/cucumber-parallel/3.json", "html:C:/Users/salesvp1/eclipse-workspace/DemoCucumberProject-master/DemoCucumberProject-master/target/cucumber-parallel/3"},
        monochrome = true,
        tags = {"@sanity"},
        glue = {"stepDefinitions"})
public class Parallel03IT extends AbstractTestNGCucumberTests {
}
