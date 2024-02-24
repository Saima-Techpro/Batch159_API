package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Patch01 extends JsonPlaceHolderBaseUrl {

     /*
        Given
           1) https://jsonplaceholder.typicode.com/todos/198
           2) {
                 "title": "Read the books"
               }
        When
         I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like
                            {
                                 "userId": 10,
                                  "id": 198,
                                  "title": "Read the books",
                                  "completed": true
                            }
     */

    @Test
    public void patch01() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        Map<String, Object> payload = jsonPlaceHolderMapper(null, "Read the books", null);  //Serialization
        System.out.println("payload: " + payload);

        //Send the request and get the response
        Response response = given(spec).body(payload).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); // De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(payload.get("title"), actualData.get("title"));

        // If you need to assert all values in response, then add the rest of key, value pair here
        // We are not sending the other values to database, but we need to add them in payload for assertion purpose
        payload.put("userId", 10);
        payload.put("completed", true);
       // then assert
        assertEquals(payload.get("userId"), actualData.get("userId"));
        assertEquals(payload.get("completed"), actualData.get("completed"));

    }
    @Test
    public void patch02() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        Map<String, Object> payload = jsonPlaceHolderMapper(null, "Read the books", null);  //Serialization
        System.out.println("payload: " + payload);

        // CREATE payload and expectedData maps separately
        Map<String, Object> expectedData = jsonPlaceHolderMapper(10, "Read the books", true);
        System.out.println("expectedData: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(payload).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); // De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
