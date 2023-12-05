package com.transgrid.test;

import com.transgrid.core.api.HttpOperation;
import com.transgrid.pojo.Request;
import io.restassured.response.Response;
import org.junit.Test;

public class TestRequest {
    @Test
    public void request(){
        Request request = Request.getRequestFromProperty("weatherapi");
        System.out.println(request);
        HttpOperation httpOperation = new HttpOperation();
        Response response = httpOperation.execute(request);
        System.out.println(response.getStatusCode());
    }
}
