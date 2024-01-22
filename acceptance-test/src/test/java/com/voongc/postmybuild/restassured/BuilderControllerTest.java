package com.voongc.postmybuild.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.voongc.postmybuild.entity.Builder;
import com.voongc.postmybuild.entity.Address;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BuilderControllerTest {
    static Builder createdBuilder = null;
    @BeforeAll
    public static void beforeAll(){
        // Check if the environment variable is set
        String baseURIHost = System.getenv("BASE_URI_HOST");
        // If the environment variable is not set or is empty, use the default from the properties file
        if (baseURIHost == null || baseURIHost.isEmpty()) {
            RestAssured.baseURI = "http://localhost:8080";
        } else {
            RestAssured.baseURI = "http://" + baseURIHost + ":8080";
        }

        System.out.println("BASE URI: " +RestAssured.baseURI);

        Address address = new Address("1", "Data St", "West Yorks", "UK", "YO1 3AB");
        // Create a sample Builder object
        Builder builder = new Builder("Before Builders Ltd", "John", "Smith", List.of(address));

        // Perform a POST request and validate the response
        createdBuilder = given()
                .contentType(ContentType.JSON)
                .body(builder)
                .when()
                .post("/builder/createBuilder")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("Before Builders Ltd"))
                .body("forename", equalTo("John"))
                .body("surname", equalTo("Smith"))
                .log()
                .all()
                .extract().response().as(Builder.class);
    }

    @Test
    public void testCreateBuilder() {
        Address address = new Address("1", "Data St", "West Yorks", "UK", "YO1 3AB");
        // Create a sample Builder object
        Builder builder = new Builder("Builders Inc.", "Chris", "Steward", List.of(address));

        // Perform a POST request and validate the response
        given()
            .contentType(ContentType.JSON)
            .body(builder)
        .when()
            .post("/builder/createBuilder")
        .then()
            .statusCode(HttpStatus.SC_CREATED)
            .body("name", equalTo("Builders Inc."))
            .body("forename", equalTo("Chris"))
            .body("surname", equalTo("Steward"))
                .log().all();
    }

    @Test
    public void testGetBuilder(){
        Builder fetchedBuilder = given()
                .get("/builder/" + createdBuilder.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .all()
                .extract().response().as(Builder.class);

        Assertions.assertEquals(createdBuilder,fetchedBuilder);
    }

    @Disabled
    @Test
    public void testUpdateBuilder(){
    }

}
