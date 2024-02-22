package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;

public class Get07 extends PetStoreBaseUrl {
        /*
    Given
        https://petstore.swagger.io/v2/pet/3467889
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
               {
                "id": 1889,
                "category": {
                    "id": 0,
                    "name": "Bird"
                },
                "name": "Tweety",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "Pets"
                    },
                    {
                        "id": 0,
                        "name": "PrettyPaws"
                    }
                ],
                "status": "pending"
                }

     */

    @Test//Import @Test annotation from Junit
    public void get07() {
        //Set the url
        spec.pathParams("first", "pet", "second", 1889);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way:
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("category.name", equalTo("Bird"),
                        "name", equalTo("Tweety"),
                        "tags[0].name",equalTo("Pets"),
                        "tags[1].name",equalTo("PrettyPaws"),
                        "photoUrls[0]", equalTo("string"),
                        "status", equalTo("pending"));

        //2nd Way:
        JsonPath jsonPath = response.jsonPath();
        assertEquals("application/json", response.contentType());
        assertEquals("Bird", jsonPath.getString("category.name"));
        assertEquals("Tweety", jsonPath.getString("name"));
        assertEquals("Pets", jsonPath.getString("tags[0].name"));
        assertEquals("PrettyPaws", jsonPath.getString("tags[1].name"));
        assertEquals("string", jsonPath.getString("photoUrls[0]"));
        assertEquals("pending", jsonPath.getString("status"));


        // We learned how to get data from nested structures of response body

         /*  HW TASK
            Given
            https://jsonplaceholder.typicode.com/users/1
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be "application/json"
		And
		    "name" is "Leanne Graham",
		And
		    "email" is "Sincere@april.biz"
        And
		    "city" is "Gwenborough"
		And
		    "lat" is "-37.3159"
        And
		    Company name  is "Romaguera-Crona"



package get_requests;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class HW1 extends JsonPlaceHolderBaseUrl {
    @Test
    public void test(){
//    i) Set the Url
        spec.pathParams("first", "users", "second","1");
//    ii) Set the Expected Data
//    iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}/{second}");
//    iv)  Do Assertions}
        JsonPath jsonPath = response.jsonPath();
        assertEquals(200,response.statusCode());
        assertTrue("application/json", response.contentType().contains("application/json"));
        assertEquals("Leanne Graham", jsonPath.getString("name"));
        assertEquals("Sincere@april.biz", jsonPath.getString("email"));
        assertEquals("Gwenborough", jsonPath.getString("address.city"));
        assertEquals("-37.3159", jsonPath.getString("address.geo.lat"));
        assertEquals("Romaguera-Crona", jsonPath.getString("company.name"));
    }
}

          */

    }


}