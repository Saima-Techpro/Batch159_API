package practice;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Practice06 extends JsonPlaceHolderBaseUrl {

    /*
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
     */

    @Test
    public void test(){
//    i) Set the Url
        spec.pathParams("first", "users", "second","1");
//    ii) Set the Expected Data
//    iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
//    iv)  Do Assertions
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

