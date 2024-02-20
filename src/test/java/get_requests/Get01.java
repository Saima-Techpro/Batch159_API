package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/*
    1. We use Postman for Manual Tests of API
    2. We use Rest Assured Library for Automation
    3. To type automation scripts, follow these steps:
        a) we should understand the requirement
        b) write your "Test Script" in Gherkin language
            Gherkin language has four keywords
            i) Given: used for pre-conditions (url, body, authorisation, content type etc....)
            ii) When: used for action (method name like Get, Post, Put, Delete etc.)
            iii) Then: used for assertions
            iv) And: used for repeating the same step (multiple use of keywords)
         c) Start to write automation script
            i) Set the URL
            ii) Set the expected data
            iii) Send the Request and get the Response
            iv) Do Assertions
 */

public class Get01 {
        /*
           Given
               https://restful-booker.herokuapp.com/booking/10
           When
               User sends a GET Request to the url
           Then
               HTTP Status Code should be 200
           And
               Content Type should be application/json
           And
               Status Line should be HTTP/1.1 200 OK
       */

    @Test
    public void get01(){//Test methods must be public and void without patameter
//        i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";

//        ii) Set the expected data

//        iii) Send the request and get the response

//        1st Way:
//        given().when().get(url).prettyPrint();  when() is OPTIONAL
//        given()
//                .when()
//                .get(url)
//                .then()
//                .statusCode(200)
//                .contentType("application/json")
//                .statusLine("HTTP/1.1 200 OK");

        // 2nd Way: (RECOMMENDED)

        Response response = given().get(url);
        System.out.println(response);  // provides reference
        response.prettyPrint();

//        iv) Do Assertion
        response
                .then()
                .statusCode(200)
//                .contentType("application/json")
                .contentType(ContentType.JSON)    //  ContentType.JSON => constant values are saved in 'enum' structure (JAVA)
                .statusLine("HTTP/1.1 200 OK");
    }


}