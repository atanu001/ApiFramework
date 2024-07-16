package apiAutomation.stepDefinition;



import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apicore.utility.CommonUtils;
import org.apicore.utility.FileHelper;
import org.apicore.utility.FreemarkerTemplateBuilder;
import org.apicore.utility.RequestFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.given;

public class apiSteps {
    private static final String BASE_URL = FileHelper.getProperty("baseurl");
    private Response response;


    @Given("user get api endpoint")
    public void userGetApiEndpoint(){


    }
    @And("user send http request with the following details")
    public void userProvideTheUrl( DataTable dataTable) throws IOException {

        List<Map<String, String>> data = dataTable.asMaps(String.class,String.class);
        String name = (String)((Map)data.get(0)).get("name");
        String cpuModel = (String)((Map)data.get(0)).get("cpuModel");
        String[] key = {"name","cpuModel"};
        String[] value = {name,cpuModel};
        Map<String, Object> valueToTemplate = CommonUtils.valuesToTemplate(key,value);
        String reqBody = FreemarkerTemplateBuilder.formTemplate(FileHelper.getProperty("testapipath")).build(valueToTemplate);
        RequestSpecification request = RequestFactory.getRequest().baseUri("https://api.restful-api.dev").contentType(ContentType.JSON).body(reqBody);
        response = request.post(FileHelper.getProperty("posturl")).then().extract().response();




    }
}
