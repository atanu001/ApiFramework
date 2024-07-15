package apiAutomation.utility;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;



public class RestUtil {
    private static String BASE_URL = null;

    public static RequestSpecification getRequestSpecification() {
        BASE_URL = FileHelper.getProperty("baseurl");
        return RestAssured.given().baseUri(BASE_URL);
    }
}
