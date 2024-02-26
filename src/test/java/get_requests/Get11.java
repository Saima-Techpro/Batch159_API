package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        And
            The female users are more than or equals to male users
    */

    @Test
    public void get(){
        // Set Url:
        spec.pathParam("first","users");
        //Set Expected Data:

        // Send the request and the get the response

        Response response  = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do assertion
        // 1st way:
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10), "data.status", hasItem("active"),
                        "data.name", hasItems("Drona Bhattacharya", "Girija Mahajan" , "Mukesh Banerjee"));

        // 2nd way: Use JsonPath to assert
        JsonPath jsonPath = response.jsonPath();
        assertEquals(10, jsonPath.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1", jsonPath.getString("meta.pagination.links.current"));
        assertEquals(10, jsonPath.getList("data.id").size());
        assertTrue(jsonPath.getList("data.status").contains("active"));

        List<String> expectedNamesList = Arrays.asList("Drona Bhattacharya", "Girija Mahajan" , "Mukesh Banerjee");
        assertTrue(jsonPath.getList("data.name").containsAll(expectedNamesList));



        // We need jsonPath for this assertion: The female users are less than or equal to male users
        // compare the number of female and male users in 2 ways:

        // 1st way: get all the genders and count the female users, then compare it with male users
        // so we need to extract that part out of the body


        List<String> genderList = jsonPath.getList("data.gender");
        System.out.println("genderList" + genderList);
        // for each loop to count the number of female

        int numberOfFemales = 0;
        for(String w: genderList){
            if(w.equals("female")){
                numberOfFemales++;
            }
        }
        System.out.println("numberOfFemales: " + numberOfFemales);
        int numberOfMales = genderList.size() - numberOfFemales;

        assertTrue(numberOfFemales >= numberOfMales);

        // 2nd way: We will get all female users by using "Groovy language" then compare it with males
        List<String> femaleList = jsonPath.getList("data.findAll{it.gender == 'female'}.gender");
        System.out.println("femaleList: " + femaleList);

        List<String> maleList = jsonPath.getList("data.findAll{it.gender == 'male'}.gender");
        System.out.println("maleList:" + maleList);

        assertTrue(femaleList.size() <= maleList.size());

        // The number of active status is more than 5
        //1st way
        List<String> statusList = jsonPath.getList("data.status");
        System.out.println(statusList);

        int statusCounter = 0;
        for(String w :statusList ){
            if(w.equals("active")){
                statusCounter++;
            }
        }
        assertTrue(statusCounter>5);

        // 2nd way: Use Groovy
        List<String> listOfActiveStatus = jsonPath.getList("data.findAll{it.status='active'}.status");
        System.out.println("listOfActiveStatus = " + listOfActiveStatus);



    }
}
