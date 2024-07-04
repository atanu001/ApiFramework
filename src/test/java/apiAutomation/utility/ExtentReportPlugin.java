package apiAutomation.utility;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestStep;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.HookTestStep;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportPlugin implements ConcurrentEventListener{
    private static ExtentTest featureTest;
    private static ExtentTest scenarioTest;
    private static Map<TestStep, ExtentTest> stepExtentTestMap = new HashMap<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        String featureName = event.getTestCase().getUri().toString();
        String scenarioName = event.getTestCase().getName();
        featureTest = ExtentReportUtil.createFeature(featureName);
        scenarioTest = ExtentReportUtil.createScenario(scenarioName);
    }

    private void handleTestStepStarted(TestStepStarted event) {
        TestStep testStep = event.getTestStep();
        String stepName = getStepName(testStep);
        ExtentTest stepTest = scenarioTest.createNode(stepName);
        stepExtentTestMap.put(testStep, stepTest);
    }

    private void handleTestStepFinished(TestStepFinished event) {
        TestStep testStep = event.getTestStep();
        ExtentTest stepTest = stepExtentTestMap.get(testStep);
        switch (event.getResult().getStatus()) {
            case PASSED:
                stepTest.pass("Passed");
                break;
            case FAILED:
                stepTest.fail(event.getResult().getError());
                break;
            case SKIPPED:
                stepTest.skip("Skipped");
                break;
            default:
                stepTest.info("Info");
                break;
        }
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        ExtentReportUtil.flushReport();
    }

    private String getStepName(TestStep testStep) {
        if (testStep instanceof PickleStepTestStep) {
            return ((PickleStepTestStep) testStep).getStep().getText();
        } else if (testStep instanceof HookTestStep) {
            return ((HookTestStep) testStep).getHookType().name();
        } else {
            return "Unknown Step";
        }
    }
}
