package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post02 extends JsonPlaceHolderBaseUrl {

    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
         }
   When
       I send POST Request to the Url

   Then
       Status code is 201
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/
    @Test
    public void post02() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data
        //We can set the payload by using Map --> This is recommended because we can get the data from expected data in assertion part dynamically.
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);
        System.out.println("expectedData = " + payload);

        //Send the request and get the response
        //We need a serializer to convert Java Object to JSON --> From Java to JSON ==> Serialization (INTERVIEW)
        // we need to add Jackson-databind dependency to pom.xml for serialization
        // We don't need to add contentType now as we have added it in our spec (in base url class)
        Response response = given(spec).body(payload).post("{first}");
        response.prettyPrint();

        //Do assertion
        //To do assertion we need 2 data in same types. We need to convert Json response into Java object
        //To convert Json to Java object --> De-Serialization
        Map<String, Object> actualData = response.as(HashMap.class);  // De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(payload.get("completed"), actualData.get("completed"));
        assertEquals(payload.get("userId"), actualData.get("userId"));
        assertEquals(payload.get("title"), actualData.get("title"));
        //We did not use any hard codding in assertion part

    }

    @Test
    public void post02b() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data
//        Map<String, Object> payload = JsonPlaceHolderTestData.jsonPlaceHolderMapper(55,"Tidy your room",false);
        Map<String, Object> payload = jsonPlaceHolderMapper(55,"Tidy your room",false);
        System.out.println("payload = " + payload);

        //Send the request and get the response
        Response response = given(spec).body(payload).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(payload.get("completed"), actualData.get("completed"));
        assertEquals(payload.get("userId"), actualData.get("userId"));
        assertEquals(payload.get("title"), actualData.get("title"));
        //We did not use any hard codding in assertion part

    }

}
