package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 {

        /*
            Given
                https://jsonplaceholder.typicode.com/todos/23
            When
                User send GET Request to the URL
            Then
                HTTP Status Code should be 200
            And
                Response format should be “application/json”
            And
                “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
            And
                “completed” is false
            And
                “userId” is 2
         */

    // First 2 Get classes are checking Metadata mainly
    // In this class, we will check Metadata as well as get into the body of the response and get and assert its values

    @Test
    public void get03() {
        //Set the url
        String url = "https://jsonplaceholder.typicode.com/todos/23";

        //Set the expected data

        //Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

        //Do assertion
        //1st way: Hard Assertion
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));

        //2nd way: Soft Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(true),
                        "userId", equalTo(21));

        /*
            1)    When you run the code Java stops the execution in the first failure.
                So, assertions after the failure will not be executed.
                But assertions before the failure will be executed and passed.
            2)   If Java stops the execution in the first failure this is called "Hard Assertion"
            3)   If Java does not stop the execution in the first failure this is called "Soft Assertion"
            4)   If you use multiple body() methods for assertion, it will work like "Hard Assertion"
            5)   If you use one single body() method with multiple assertion, it will work like "Soft Assertion"
         */


    }
}
