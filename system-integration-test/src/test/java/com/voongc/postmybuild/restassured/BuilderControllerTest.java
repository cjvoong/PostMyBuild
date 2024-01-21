package com.voongc.postmybuild.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import com.voongc.postmybuild.data.entity.Address;
import com.voongc.postmybuild.data.entity.Builder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BuilderControllerTest {

    @Test
    public void testCreateBuilder() {
        RestAssured.baseURI ="http://localhost:8080";
        Address address = new Address("1", "Data St", "West Yorks", "UK", "YO1 3AB");
        // Create a sample Builder object
        Builder builder = new Builder("John", "Doe", "Smith", List.of(address));

        // Perform a POST request and validate the response
        given()
            .contentType(ContentType.JSON)
            .body(builder)
        .when()
            .post("/builder/createBuilder")
        .then()
            .statusCode(HttpStatus.SC_CREATED)
            .body("builderName", equalTo("John"))
            .body("builderForename", equalTo("Doe"))
            .body("builderSurname", equalTo("Smith"))
                .log().all();
    }
}
