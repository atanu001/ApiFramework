package apiAutomation.utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentTest featureTest;
    private static ExtentTest scenarioTest;

    public static ExtentReports initializeExtentReport() {
        String reportPath = "cucumber-report/report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Cucumber Extent Report");
        sparkReporter.config().setReportName("Cucumber Extent Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    public static ExtentTest createFeature(String featureName) {
        featureTest = extent.createTest(featureName);
        return featureTest;
    }

    public static ExtentTest createScenario(String scenarioName) {
        scenarioTest = featureTest.createNode(scenarioName);
        return scenarioTest;
    }
    public static void logStep(String stepStatus, String message) {
        if (stepStatus.equalsIgnoreCase("pass")) {
            scenarioTest.pass(message);
        } else if (stepStatus.equalsIgnoreCase("fail")) {
            scenarioTest.fail(message);
        } else if (stepStatus.equalsIgnoreCase("skip")) {
            scenarioTest.skip(message);
        } else {
            scenarioTest.info(message);
        }
    }

    public static void flushReport() {
        extent.flush();
    }


}
