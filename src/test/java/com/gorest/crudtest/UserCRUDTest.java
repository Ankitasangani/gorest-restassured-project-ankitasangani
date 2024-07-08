package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = TestUtils.getRandomValue() + "Garud Mehrotra";
    static String gender = "male";
    static String status = "active";
    String email = TestUtils.getRandomValue() + "mehrotra_garud@erdman.example";

    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 2633d0058d2069545c8073896cbd4e12533b9b68bab8bb0ef0b969588bd7f7af")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void verifyUserUpdatedSuccessfully() {
        String name = UserCRUDTest.name + "Updated";

        int storeId = 7017753;
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", storeId)
                .header("Authorization", "Bearer 2633d0058d2069545c8073896cbd4e12533b9b68bab8bb0ef0b969588bd7f7af")
                .when()
                .body(userPojo)
                .put("/users/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);
    }

    @Test
    public void verifyUserDeletedSuccessfully() {

        given()
                .pathParam("id", 7015031)
                .header("Authorization", "Bearer 2633d0058d2069545c8073896cbd4e12533b9b68bab8bb0ef0b969588bd7f7af")
                .when()
                .delete("/users/{id}")
                .then().log().ifValidationFails().statusCode(204);


        given()
                .pathParam("id", 7015031)
                .header("Authorization", "Bearer 2633d0058d2069545c8073896cbd4e12533b9b68bab8bb0ef0b969588bd7f7af")
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(404);
    }
}
