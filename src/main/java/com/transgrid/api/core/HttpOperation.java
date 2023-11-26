package com.transgrid.api.core;

import com.transgrid.api.constants.AuthType;
import com.transgrid.api.pojo.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class HttpOperation {

    public Response execute(Request request) {
        RequestSpecification requestSpecification = getRequestSpecification(request.getAuthType(), request.getAuthParam());
        requestSpecification.baseUri(request.getBaseUri());
        requestSpecification.basePath(request.getBasePath());

        if(!request.getQueryParam().isEmpty()) requestSpecification.queryParams(request.getQueryParam());
        if(!request.getPathParam().isEmpty()) requestSpecification.pathParams(request.getPathParam());
        if(!request.getHeaders().isEmpty()) requestSpecification.headers(request.getHeaders());
        if (request.isLogging()) requestSpecification.log().all();
        if (!request.getRequestBody().isEmpty())requestSpecification.body(request.getRequestBody());

        Response response = switch (request.getMethod()) {
            case GET -> requestSpecification.get(request.getEndPoint());
            case POST -> requestSpecification.contentType(ContentType.JSON).post(request.getEndPoint());
            case DELETE -> requestSpecification.delete(request.getEndPoint());
            case PUT -> requestSpecification.put(request.getEndPoint());
            case PATCH -> requestSpecification.patch(request.getEndPoint());
        };
        if(request.isLogging()) response.then().log().all();
        return response;

    }

    private RequestSpecification getRequestSpecification(AuthType authType, Map<String, String> authParam) {
        return switch (authType) {
            case NO_AUTH -> RestAssured.given().auth().none();
            case BASIC -> RestAssured.given().auth().basic(authParam.get("username"), authParam.get("password"));
            default -> RestAssured.given();
        };
    }
}
