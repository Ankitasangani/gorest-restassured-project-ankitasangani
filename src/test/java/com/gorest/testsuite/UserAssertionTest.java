package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Assert the following:
 * 1. Verify if the total record is 20
 * 2. Verify if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
 * 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
 * 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
 * 5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
 * 6. Verify the status is “Active” of username is “Amaresh Rana”
 * 7. Verify the Gender = male of username is “Dhanalakshmi Pothuvaal”
 */
public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    // 1) Verify if the total record is 20
    @Test
    public void verifyTotalRecord() {
        response.body("", hasSize(20));
    }

    // 2) Verify if the name of id = 7015038 is equal to "Garud Mehrotra"
    @Test
    public void verifyNameOfID() {
        response.body("find{it.id == 7015038}.name", equalTo("Garud Mehrotra"));
    }

    // 3) Check the single ‘name’ in the Array list (Poornima Devar)
    @Test
    public void checkSingleName() {
        response.body("name", hasItem("Poornima Devar"));
    }

    // 4) Check the multiple ‘name’ in the ArrayList (Poornima Devar, Deeptendu Talwar, Achyut Kakkar Sr.)
    @Test
    public void checkMultipleName() {
        response.body("name", hasItems("Poornima Devar", "Deeptendu Talwar", "Achyut Kakkar Sr."));
    }

    // 5) Verify the email of userid = 7015023 is equal “pilla_aamod@collier-ferry.example”
    @Test
    public void verifyEmail() {
        response.body("find{it.id == 7015023}.email", equalTo("pilla_aamod@collier-ferry.example"));
    }

    // 6) Verify the status is “Active” of username is “Poornima Devar”
    @Test
    public void verifyStatus() {
        response.body("find{it.name == 'Poornima Devar'}.status", equalTo("active"));
    }

    // 7) Verify the Gender = male of user name is “Deeptendu Talwar”
    @Test
    public void verifyGender() {
        response.body("find{it.name == 'Deeptendu Talwar'}.gender", equalTo("male"));
    }

}
