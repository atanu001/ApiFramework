package apiAutomation.utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import org.apache.commons.io.output.WriterOutputStream;


public class ExtentReportUtil {
    /*private static ExtentReports extent;
    private static ExtentTest featureTest;
    private static ExtentTest scenarioTest;
    private static ExtentTest test;

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
            scenarioTest.log(Status.PASS,message);
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
    }*/
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ByteArrayOutputStream requestOutputStream;
    private static ByteArrayOutputStream responseOutputStream;
    private static PrintStream requestPrintStream;
    private static PrintStream responsePrintStream;

    static {
        initializeReport();
    }

    public static void initializeReport() {
        String reportPath = "cucumber-report/report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Cucumber Extent Report");
        sparkReporter.config().setReportName("Cucumber Extent Report");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void logTestInfo(String info) {
        test.info(info);
    }

    public static RequestSpecification addRequestLogging(RequestSpecification request) {
        requestOutputStream = new ByteArrayOutputStream();
        requestPrintStream = new PrintStream(requestOutputStream);
        request.filter(new RequestLoggingFilter(requestPrintStream));
        return request;
    }

    public static RequestSpecification addResponseLogging(RequestSpecification request) {
        responseOutputStream = new ByteArrayOutputStream();
        responsePrintStream = new PrintStream(responseOutputStream);
        request.filter(new ResponseLoggingFilter(responsePrintStream));
        return request;
    }

    public static void logRequest() {
        test.info("Request: " + requestOutputStream.toString());
    }

    public static void logResponse(Response response) {
        String responseBody = response.getBody().asPrettyString();
        test.info("Response: " + responseBody);
        test.info("Response Details: " + responseOutputStream.toString());
    }

    public static void flushReport() {
        extent.flush();
    }
}
