package testRunner;

import apiAutomation.utility.ExtentReportUtil;
import io.cucumber.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/apiAutomation/features", glue = { "apiAutomation.stepDefinition"}, plugin = { "json:target/cucumber.json", "pretty", "html:target/cucumber.html", "apiAutomation.utility.ExtentReportPlugin" }, monochrome = true, publish = true)
public class junitRunner {

    @BeforeClass
    public static void setup() {
        ExtentReportUtil.initializeExtentReport();
    }

    @AfterClass
    public static void teardown() {
        // Flush Extent Reports after all tests are finished
        ExtentReportUtil.flushReport();
    }
}
