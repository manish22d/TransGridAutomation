package com.transgrid.test;

import com.transgrid.api.core.HttpOperation;
import com.transgrid.api.pojo.Request;
import io.restassured.response.Response;
import org.junit.Test;

public class TestRequest {
    @Test
    public void request(){
        Request request = Request.getRequestFromBody("weatherapi");
        System.out.println(request);
        HttpOperation httpOperation = new HttpOperation();
        Response response = httpOperation.execute(request);
        System.out.println(response.getStatusCode());
    }
}
