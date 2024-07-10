package apiAutomation.stepDefinition;

import apiAutomation.utility.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class apiSteps {
    private static final String BASE_URL = FileHelper.getProperty("baseurl");
    private static RequestSpecification request;
    private Response response;

    @Given("user get api endpoint")
    public void userGetApiEndpoint(){
        request = given().baseUri(BASE_URL).basePath("/employees");
        ExtentReportUtil.createTest("I set GET employee service api endpoint");
        ExtentReportUtil.logTestInfo("Set GET employee service API endpoint");
    }
    @And("user send http request with the following details")
    public void userProvideTheUrl( DataTable dataTable) throws IOException {

        ExtentReportUtil.addRequestLogging(request);
        ExtentReportUtil.addResponseLogging(request);
        List<Map<String, String>> data = dataTable.asMaps(String.class,String.class);
        String name = (String)((Map)data.get(0)).get("name");
        String cpuModel = (String)((Map)data.get(0)).get("cpuModel");
        String[] key = {"name","cpuModel"};
        String[] value = {name,cpuModel};
        Map<String, Object> valueToTemplate = CommonUtils.valuesToTemplate(key,value);
        String reqBody = FreemarkerTemplateBuilder.formTemplate(FileHelper.getProperty("testapipath")).build(valueToTemplate);
        Response response = given().baseUri("https://api.restful-api.dev").contentType(ContentType.JSON).body(reqBody).post(FileHelper.getProperty("posturl")).then().extract().response();
        //Log.info("Response Status Code: " + response.getStatusCode());
        //Log.info("Response Body: " + response.getBody().asString());
        ExtentReportUtil.logRequest();
        ExtentReportUtil.logResponse(response);
        ExtentReportUtil.logTestInfo("Sent POST HTTP request");
        //System.out.print("Hi there!!" + url);



    }
}
