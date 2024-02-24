package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
         }
      3) Content type should be json
   When
       I send POST Request to the Url

   Then
       Status code is 201
   And
       response body is like
                    {
                       "userId": 55,
                       "title": "Tidy your room",
                       "completed": false,
                       "id": 201
                     }
*/

//    {"userId": 55, "title": "Tidy your room", "completed": false}
    @Test
    public void post01() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data --> Payload --> The data to transfer
        //We can create the payload by String but it is not Recommended!
        // Because it is not going to be dynamic to get the data in assertion.
        String payload = " {\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false}";

        System.out.println("payload = " + payload);


        //Send the request and get the response
        //In sending post request, the contentType must be declared..
        // Show how we can put contentType(ContentType.JSON) at various points ... in baseurl class, or in spec at line 42 or here in the following line while sending request id it's specific to only this request
        Response response = given(spec).contentType(ContentType.JSON).body(payload).post("{first}");
        response.prettyPrint();

        //Do assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(201, response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        assertTrue(response.contentType().contains("application/json"));
        assertEquals(55, jsonPath.getInt("userId"));
        assertEquals("Tidy your room", jsonPath.getString("title"));
        assertFalse(jsonPath.getBoolean("completed"));
        // we can't assert ids in real life coz it's assigned by the system automatically


    }

}
