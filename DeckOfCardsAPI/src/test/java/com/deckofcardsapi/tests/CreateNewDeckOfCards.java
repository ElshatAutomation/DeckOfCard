package com.deckofcardsapi.tests;

import com.deckofcardsapi.pojo.*;
import com.deckofcardsapi.restclient.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.testng.*;
import org.testng.annotations.*;

import java.util.*;

public class CreateNewDeckOfCards {

    String baseURI = "https://deckofcardsapi.com";
    String basePath = "/api/deck/new/";

    @Test
    public void createNewDeckTest(){
        Response response = RestClient.doGet("JSON", baseURI, basePath, null, null, true);
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int remaining = jsonPathEvaluator.get("remaining");
        Assert.assertEquals(remaining,52);
    }

    @Test
    public void getJokerTest(){
        Map<String, Boolean> params = new HashMap<String, Boolean>();
        params.put("jokers_enabled", true);
        Response response = RestClient.doGet("JSON", baseURI, basePath, null, params, true);
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int remaining = jsonPathEvaluator.get("remaining");
        Assert.assertEquals(remaining,54);
    }

    @Test
    public void postJokerTest(){
        Joker joker = new Joker("true");
        Response response = RestClient.doPost("JSON", baseURI, basePath, null, null, true, joker);
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int remaining = jsonPathEvaluator.get("remaining");
        Assert.assertEquals(remaining,52);
    }

}
