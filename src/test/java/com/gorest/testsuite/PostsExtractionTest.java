package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Extraction Example
 * 1. Extract the title
 * 2. Extract the total number of record
 * 3. Extract the body of 15th record
 * 4. Extract the user_id of all the records
 * 5. Extract the title of all the records
 * 6. Extract the title of all records whose user_id = 5914200
 * 7. Extract the body of all records whose id = 93957
 */
public class PostsExtractionTest {
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

    // 1) Extract the title
    @Test
    public void extractAllTitles() {
        List<String> listOfTitle = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of the titles are:" + listOfTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    // 2) Extract the total number of record
    @Test
    public void extractNumberOfRecord() {
        List<?> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of record is:" + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 3) Extract the body of 15th record
    @Test
    public void extractBodyOfRecord() {
        String body = response.extract().path("[14].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is:" + body);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Extract the user_id of all the records
    @Test
    public void extractAllUserIds() {
        List<Integer> listOfUserId = response.extract().path("user_id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of User IDs are:" + listOfUserId);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5) Extract the title of all the records
    @Test
    public void extractTitles() {
        List<String> listOfTitle = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list Of Titles are:" + listOfTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Extract the title of all records whose user_id = 7017697
    @Test
    public void extractTitle() {
        List<String> title = response.extract().path("findAll{it.user_id == 7017697}.title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 7017697 is: " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Extract the body of all records whose id = 140182
    @Test
    public void extractBody() {
        List<String> body = response.extract().path("findAll{it.id == 140182}.body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 140182 is: " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
