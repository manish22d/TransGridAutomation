package com.transgrid.pojo;

import com.jayway.jsonpath.JsonPath;
import com.transgrid.constants.AuthType;
import com.transgrid.constants.Method;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Request {
    String endPoint;
    String requestBody = "";
    Map<String, String> queryParam = new HashMap<>();
    Map<String, String> pathParam = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    Method method;
    String baseUri;
    String basePath;
    AuthType authType;
    Map<String, String> authParam = new HashMap<>();
    boolean logging;

    public static Request getRequestFromProperty(String apiName) {
        apiName = apiName.endsWith(".properties") ? apiName : apiName.concat(".properties");
        InputStream is = ClassLoader.getSystemResourceAsStream("apiConfig/" + apiName);
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Given Property file is not found -> "+apiName);
        }
        Request request = new Request();
        request.setBaseUri(prop.getProperty("api.baseurl"));
        request.setBasePath(prop.getProperty("api.basePath"));
        request.setEndPoint(prop.getProperty("api.endpoint"));
        request.setLogging(prop.getProperty("logging"));
        request.setMethod(Method.getMethodUsingName(prop.getProperty("httpMethod")));
        request.setAuthType(AuthType.getAuthUsingName(prop.getProperty("authType")));
        request.setAuthParam(prop.entrySet().stream().filter(set-> set.getKey().toString().startsWith("api.auth.")).collect(Collectors.toMap(entry -> entry.getKey().toString().replace("api.auth.",""), entry -> entry.getValue().toString())));
        request.setHeaders(prop.entrySet().stream().filter(set-> set.getKey().toString().startsWith("api.headers.")).collect(Collectors.toMap(entry -> entry.getKey().toString().replace("api.headers.",""), entry -> entry.getValue().toString())));
        request.setQueryParam(prop.entrySet().stream().filter(set-> set.getKey().toString().startsWith("api.queryparam.")).collect(Collectors.toMap(entry -> entry.getKey().toString().replace("api.queryparam.",""), entry -> entry.getValue().toString())));
        request.setPathParam(prop.entrySet().stream().filter(set-> set.getKey().toString().startsWith("api.pathparam.")).collect(Collectors.toMap(entry -> entry.getKey().toString().replace("api.pathparam.",""), entry -> entry.getValue().toString())));


        if(!prop.getProperty("requestBody", "").isEmpty()) {
            try {
                request.setRequestBody(IOUtils.resourceToString(prop.getProperty("requestBody"), Charset.defaultCharset()));
            } catch (IOException e) {
                throw new RuntimeException("Could not found given file -> "+prop.getProperty("requestBody"));
            }

        }
        return request;
    }

    public Map<String, String> getAuthParam() {
        return authParam;
    }

    public void setAuthParam(Map<String, String> authParam) {
        this.authParam = authParam;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, String> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(Map<String, String> queryParam) {
        this.queryParam = queryParam;
    }

    public Map<String, String> getPathParam() {
        return pathParam;
    }

    public void setPathParam(Map<String, String> pathParam) {
        this.pathParam = pathParam;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }



    public boolean isLogging() {
        return logging;
    }

    public void setLogging(String logging) {
        if(logging.isEmpty())
            this.logging = false;
        this.logging = Boolean.parseBoolean(logging);
    }

    @Override
    public String toString() {
        return "Request{" +
                "endPoint='" + endPoint + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", queryParam=" + queryParam +
                ", pathParam=" + pathParam +
                ", headers=" + headers +
                ", method=" + method +
                ", baseUri='" + baseUri + '\'' +
                ", basePath='" + basePath + '\'' +
                ", authType=" + authType +
                ", authParam=" + authParam +

                ", logging=" + logging +
                '}';
    }

    public void updateRequest(String path, String userId) {
//        JsonPath.from(requestBody).setRootPath()
        requestBody = JsonPath.parse(requestBody).set(path, userId).jsonString();

    }
}
