package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;
public class Post07WithUtils extends JsonPlaceHolderBaseUrl {
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
    public void post()  {
        // Set Url:
        spec.pathParam("first","todos");
        // Set Expected Data:
        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("payLoad = " + payLoad);
        // Send Response And Get Request:
        Response response =given(spec).body(payLoad).when().post("{first}");
        // Do Assertion:
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());
    }
}