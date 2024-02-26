package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post06ObjectMapperPojo extends JsonPlaceHolderBaseUrl {
    /*
Given
 1) https://jsonplaceholder.typicode.com/todos
 2) {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
     }
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
    public void post() throws JsonProcessingException {
        // Set Url
        spec.pathParam("first","todos");
        // Set Expected Data:
        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        // Send Request And Get Response:
        Response response =  given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
//        System.out.println("response.asString() = " + response.asString());

        // Do Assertion:
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValue();
        JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());


    }
}
