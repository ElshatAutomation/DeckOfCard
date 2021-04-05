package com.deckofcardsapi.tests;

import com.deckofcardsapi.restclient.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.testng.*;
import org.testng.annotations.*;

public class DrawCardsFromDeck {

    String baseURI = "https://deckofcardsapi.com";
    String basePath = "/api/deck/new/";


    @Test(priority = 1)
    public void getDeckIdTest() {
        Response response = RestClient.doGet("JSON", baseURI, basePath, null, null, true);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String deck_id = jsonPathEvaluator.get("deck_id");
        System.out.println("Deck id is " + deck_id);
        String basePath = "/api/deck/" + deck_id +"/draw/";
        Response respon = RestClient.doGet("JSON", baseURI, basePath, null, null, true);
        System.out.println(respon.prettyPrint());
        Assert.assertEquals(respon.statusCode(),200);
        Assert.assertEquals(respon.contentType(),"application/json");
    }

}
