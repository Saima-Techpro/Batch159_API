package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
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
    */

    @Test
    public void get05() {
        //Set the url
        //https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith
        spec
                .pathParam("first", "booking")
                .queryParams("firstname", "John", "lastname", "Smith");

        //Set the expected data


        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();


        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("bookingid", hasSize(greaterThan(0)));//hasSize(greaterThan(0)) method checks if the size of the bookingIds is greater than 0


        //OR
        assertTrue(response.asString().contains("bookingid"));//If the response body contains "bookingId", it means body is not empty.


    }
}