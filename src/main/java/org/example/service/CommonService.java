package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.log.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class CommonService {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";

    private final Function<String, String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given().auth().oauth2("special-key");
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }

    protected Response getRequest(String uri) {
        Log.info("Sending get request to the URL " + uri);
        Response response =  requestSpecification.expect().log().ifError()
                .when().get(prepareUri.apply(uri));
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    protected Response postRequest(String uri, Object body) {
        Log.info("Sending post request to the URL " + uri);
        Response response = requestSpecification.body(body).expect().log().ifError()
                .when().post(prepareUri.apply(uri));
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    protected Response putRequest(String uri, Object body) {
        Log.info("Sending put request to the URL " + uri);
        Response response = requestSpecification.body(body).expect().log().ifError()
                .when().put(prepareUri.apply(uri));
        Log.info("Response status code is " + response.statusCode());
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    protected void deleteRequest(String uri) {
        Log.info("Sending delete request to the URL " + uri);
        requestSpecification.expect().log().ifError()
                .when().delete(prepareUri.apply(uri));
    }
}
