package apiAutomation.stepDefinition;

import apiAutomation.utility.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class apiSteps {
   public FileHelper fileHelper;
   public Properties prop;

    @Given("user provide the following details")
    public void userProvideTheUrl( DataTable dataTable) throws IOException {
        fileHelper = new FileHelper();
        prop = fileHelper.init_prop();
        //ExtentReportUtil.logStep("pass", "Verified login successful");
        List<Map<String, String>> data = dataTable.asMaps(String.class,String.class);
        String name = (String)((Map)data.get(0)).get("name");
        String cpuModel = (String)((Map)data.get(0)).get("cpuModel");
        String[] key = {"name","cpuModel"};
        String[] value = {name,cpuModel};
        Map<String, Object> valueToTemplate = CommonUtils.valuesToTemplate(key,value);
        String reqBody = FreemarkerTemplateBuilder.formTemplate(prop.getProperty("testapipath")).build(valueToTemplate);
        Response response = given().baseUri("https://api.restful-api.dev").contentType(ContentType.JSON).body(reqBody).post(prop.getProperty("posturl")).then().extract().response();
        Log.info("Response Status Code: " + response.getStatusCode());
        Log.info("Response Body: " + response.getBody().asString());
        //System.out.print("Hi there!!" + url);

    }
}
