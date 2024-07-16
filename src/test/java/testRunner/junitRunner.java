package testRunner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/apiAutomation/features", glue = { "apiAutomation.stepDefinition","apiAutomation.hooks"}, plugin =  {"pretty","html:target/cucumber-reports.html", "json:target/jsonReports/cucumber-report.json" })
public class junitRunner {

}
