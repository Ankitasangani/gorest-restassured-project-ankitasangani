package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Extraction Example
 * 1. Extract the All Ids
 * 2. Extract the all Names
 * 3. Extract the name of 5th object
 * 4. Extract the names of all object whose status = inactive
 * 5. Extract the gender of all the object whose status = active
 * 6. Print the names of the object whose gender = female
 * 7. Get all the emails of the object where status = inactive
 * 8. Get the ids of the object where gender = male
 * 9. Get all the status
 * 10. Get email of the object where name = Lal Dwivedi
 * 11. Get gender of id = 5914189
 */
public class UserExtractionTest {

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

    // 1) Extract the All Ids
    @Test
    public void extractAllIds() {
        List<Integer> listOfId = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of IDs are: " + listOfId);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the all Names
    @Test
    public void extractAllNames() {
        List<String> listOfName = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the names are: " + listOfName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 3) Extract the name of 5th object
    @Test
    public void extractNameOfObject() {
        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The 5th object name is: " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Extract the names of all object whose status = inactive
    @Test
    public void extractNameOfInactiveUsers() {
        List<String> status = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of object names of all object whose status = inactive are: " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5) Extract the gender of all the object whose status = active
    @Test
    public void extractGenderOfActiveUsers() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of gender of all the object whose status = active are: " +gender);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Print the names of the object whose gender = female
    @Test
    public void extractNameOfFemaleUsers() {
        List<String> gender = response.extract().path("findAll{it.gender == 'female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of names of the object whose gender = female are: " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the emails of the object where status = inactive
    @Test
    public void extractEmailOfInactiveUsers() {
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list the emails of the object where status = inactive are: " +email);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the ids of the object where gender = male
    @Test
    public void extractIdsOfMaleUsers() {
        List<?> listOfId = response.extract().path("findAll{it.gender == 'male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of ids of the object where gender = male are: " + listOfId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get all the status
    @Test
    public void extractAllStatus() {
        List<String> allStatus = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of status: " + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get email of the object where name = Garud Mehrotra
    @Test
    public void extractEmailOfUser() {

        String email = response.extract().path("find{it.name == 'Garud Mehrotra'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Lila Jain is: " +email);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get gender of id = 7015032
    @Test
    public void extractGenderOfUser() {
        String gender = response.extract().path("find{it.id == 7015032}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of id = 7015032 is: " +gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
