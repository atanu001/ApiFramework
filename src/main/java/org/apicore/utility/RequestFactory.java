package org.apicore.utility;


import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import lombok.Getter;

public class RequestFactory {
    private static final ThreadLocal<RequestSpecification> requestThreadLocal = new ThreadLocal<>();
    @Getter
    private static CustomLoggingFilter loggingFilter;

    public static void init() {
        loggingFilter = new CustomLoggingFilter();
        RequestSpecification request = RestAssured.given().filter(loggingFilter);
        requestThreadLocal.set(request);
    }

    public static void cleanup() {
        requestThreadLocal.remove();
        loggingFilter = null;
    }

    public static RequestSpecification getRequest() {
        return requestThreadLocal.get();
    }



    public static boolean isRequestMade() {
        return requestThreadLocal.get() != null;
    }

}

