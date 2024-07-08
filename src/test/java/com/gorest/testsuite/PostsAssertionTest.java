package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Assert the following:
 * <p>
 * 1. Verify the if the total record is 25
 * 2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupticohaero libero.”
 * 3. Check the single user_id in the Array list (5914249)
 * 4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
 * 5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
 * Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa ususvos.
 * Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
 * tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
 * acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
 */
public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

    // 1) Verify if the total record is 25
    @Test
    public void verifyTotalRecord() {
        response.body("", hasSize(25));
    }

    // 2) Verify the if the title of id = 140183 is equal to ”Adstringo tenuis consuasor sulum sunt absens bis.”
    @Test
    public void verifyTitleOfID() {
        response.body("find{it.id == 140183}.title", equalTo("Adstringo tenuis consuasor sulum sunt absens bis."));
    }

    // 3) Check the single user_id in the Array list (7017696)
    @Test
    public void checkSingleName() {
        response.body("user_id", hasItem(7017696));
    }

    // 4) Check the multiple ids in the ArrayList (140184, 140183, 140182)
    @Test
    public void checkMultipleName() {
        response.body("id", hasItems(140184, 140183, 140182));
    }

    // 5) Verify the body of userid = 140181 is equal “Cariosus ventosus tremo. Paulatim vigor tergiversatio.
    // Sunt tabgo rem. Tubineus tum maxime. Qui nobis armarium.Cerno cibo sophismata. Cognomen corona asper.
    // Terror versus deporto. Quo video certo. Corpus una vaco.”
    @Test
    public void verifyEmail() {
        response.body("find{it.id == 140181}.body", equalTo("Cariosus ventosus tremo. Paulatim vigor tergiversatio. Sunt tabgo rem. Tubineus tum maxime. Qui nobis armarium. Cerno cibo sophismata. Cognomen corona asper. Terror versus deporto. Quo video certo. Corpus una vaco."));
    }

}

















