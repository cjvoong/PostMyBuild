package com.voongc.postmybuild.restassured;

import com.voongc.postmybuild.entity.Address;
import com.voongc.postmybuild.entity.Builder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AddressControllerTest {
    static Builder createdBuilder = null;
    static Long builderId = 0L;
    static Long addressId = 0L;
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

        Address address = new Address("1", "Data St", "West Yorks", "UK", "YO1 3AB");
        // Create a sample Builder object
        Builder builder = new Builder("John", "Doe", "Smith", List.of(address));
        // Perform a POST request and add some data
        createdBuilder = given()
                .contentType(ContentType.JSON)
                .body(builder)
                .when()
                .post("/builder/createBuilder")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("John"))
                .body("forename", equalTo("Doe"))
                .body("surname", equalTo("Smith"))
                .log()
                .all()
                .extract().response().as(Builder.class);

        Assertions.assertEquals(1,createdBuilder.getAddresses().size());

        builderId = createdBuilder.getId();
        addressId = createdBuilder.getAddresses().get(0).getId();
    }
    @Test
    public void testAddAddress(){
        Address existingAddress = createdBuilder.getAddresses().get(0);
        Address newAddress = new Address("1","New St","West Yorks","UK","LS11TT");
        Builder updatedBuilder = given()
                .contentType(ContentType.JSON)
                .body(newAddress)
                .when()
                .post("/builder/" + builderId + "/addAddress")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .all()
                .extract().response().as(Builder.class);

        Assertions.assertEquals(2,updatedBuilder.getAddresses().size());
        Assertions.assertEquals(existingAddress.getHouseNo(),updatedBuilder.getAddresses().get(0).getHouseNo());
        Assertions.assertEquals(existingAddress.getHouseNo(),updatedBuilder.getAddresses().get(0).getHouseNo());
        Assertions.assertEquals(newAddress.getHouseNo(),updatedBuilder.getAddresses().get(1).getHouseNo());
        Assertions.assertEquals(newAddress.getStreet(),updatedBuilder.getAddresses().get(1).getStreet());

    }

    @Disabled
    @Test
    public void testUpdateAddress(){

    }

    @Disabled
    @Test
    public void testGetAllAddresses(){

    }
}
