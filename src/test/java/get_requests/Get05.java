package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"

           Among the data there should be someone whose firstname is "Jane" and lastname is "Doe"
    */

    // API Document for this site: https://restful-booker.herokuapp.com/apidoc/index.html
    // Show this and explain what type of API requests are allowed ..
    // Explain the differrence between path params and query params

    @Test
    public void get05() {
        //Set the url
        //https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith
        spec
                .pathParam("first", "booking")
                .queryParams("firstname", "Jane", "lastname", "Doe");

        //Set the expected data


        //Send the request and get the response
//        Response response = given().spec(spec).get("{first}");
        Response response = given(spec).get("{first}");
        response.prettyPrint();   // It returns all bookings done by John Smith


        //Do assertion
        response
                .then()
                .statusCode(200).body(containsString("bookingid"))
                .body("bookingid", hasSize(greaterThan(0)));   //hasSize(greaterThan(0)) method checks if the size of the bookingIds is greater than 0


        //OR
        assertTrue(response.asString().contains("bookingid"));  //If the response body contains "bookingid", it means body is not empty.
                                                                    // It returns all bookings done by John Smith


    }
}