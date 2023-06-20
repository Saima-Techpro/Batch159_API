package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {
  /*
       Given
           https://restful-booker.herokuapp.com/booking/0
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Response body contains "Not Found"
       And
           Response body does not contain "TechProEd"
       And
           Server is "Cowboy"
    */

    @Test
    public void get02() {
        //Set the url
        String url = "https://restful-booker.herokuapp.com/booking/0";

        //Set the expected data

        //Send the request and get the response
        Response response = given().get(url);
        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(404)//HTTP Status code should be 404
                .statusLine("HTTP/1.1 404 Not Found");//Status Line should be HTTP/1.1 404 Not Found


        //Response body contains "Not Found"
        String responseBody = response.asString();

        //With assertEquals() method we compare 2 parameters. If they are equals test will pass. If they are NOT equals test will fail.
        //We can use 3 parameters for customizing the error message.
        assertEquals("Body did not match", "Not Found", responseBody);

        //OR
        //With assertTrue() method we check if the condition is true. If if the condition is true test will pass. Otherwise test will fail.
        assertTrue(responseBody.contains("Not Found"));

        // Response body does not contain "TechProEd"
        //With assertNotEquals() method we compare 2 parameters. If they are NOT equals test will pass. If they are equals test will fail.
        assertNotEquals("TechProEd", responseBody);

        //OR
        //With assertFalse() method we check if the condition is false. If if the condition is false test will pass. Otherwise test will fail.
        assertFalse(responseBody.contains("TechProEd"));

        //Server is "Cowboy"
        String server = response.header("Server");

        assertEquals("Cowboy", server);

    }
}