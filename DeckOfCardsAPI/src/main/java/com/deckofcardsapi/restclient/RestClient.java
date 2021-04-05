package com.deckofcardsapi.restclient;

import com.deckofcardsapi.util.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import java.util.*;

public class RestClient {

    public static Response doGet(String contentType, String baseURI, String basePath, String token,
                                 Map<String, Boolean> paramMap, boolean log) {

        if(setBaseURI(baseURI)) {
            RequestSpecification request = createRequest(contentType,token,paramMap,log);
            return getResponse("GET", request, basePath);
        }

        return null;
    }

    public static Response doPost(String contentType, String baseURI, String basePath, String token,
                                  Map<String, Boolean> paramMap, boolean log, Object obj) {

        if(setBaseURI(baseURI)) {
            RequestSpecification request = createRequest(contentType,token,paramMap,log);
            addRequestPayload(request,obj);
            return getResponse("POST", request, basePath);
        }

        return null;
    }

    public static void addRequestPayload(RequestSpecification request, Object obj){
        String jsonPayload = TestUtil.getSerializedJSON(obj);
        request.body(jsonPayload);
    }

    public static boolean setBaseURI(String baseURI) {

        if(baseURI==null || baseURI.isEmpty()) {
            System.out.println("Base URI is not assigned, please check your parameter");
            return false;
        }

        try {
            RestAssured.baseURI = baseURI;
            return true;
        } catch(Exception e) {
            System.out.println("some exception occured while setting up base URI.");
            return false;
        }
    }

    public static RequestSpecification createRequest(String contentType, String token, Map<String, Boolean> paramMap, boolean log) {

        RequestSpecification request;
        if(log) {
            request = RestAssured.given().log().all();
        }else {
            request = RestAssured.given();
        }

        if(token != null) {
            request.header("Authorization","Bearer " + token);
        }

        if(paramMap != null) {
            request.queryParams(paramMap);
        }

        if(contentType.equalsIgnoreCase("JSON")) {
            request.contentType(ContentType.JSON);
        }else if(contentType.equalsIgnoreCase("XML")) {
            request.contentType(ContentType.XML);
        }else if(contentType.equalsIgnoreCase("TEXT")) {
            request.contentType(ContentType.TEXT);
        }

        return request;
    }

    public static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
        return executeAPI(httpMethod,request,basePath);
    }

    public static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
        Response response = null;
        switch(httpMethod) {

            case "GET":
                response = request.get(basePath);
                break;

            case "POST":
                response = request.post(basePath);
                break;

            case "PUT":
                response = request.put(basePath);
                break;

            case "DELETE":
                response = request.delete(basePath);
                break;

            default:
                System.out.println("HTTP method is incorrect, please check your request type");
        }

        return response;
    }
}
