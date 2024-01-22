package com.voongc.postmybuild.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SomeTest {
    @Test
    public void someTest(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        given()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}