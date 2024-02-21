package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {

        /*
             Given
                 https://jsonplaceholder.typicode.com/todos
             And
                 Accept type is “application/json”
             When
                  I send a GET request to the Url
             Then
                 HTTP Status Code should be 200
             And
                 Response format should be "application/json"
             And
                 There should be 200 todos
             And
                 "quis eius est sint explicabo" should be one of the todos title
             And
                 2, 7, and 9 should be among the userIds
          */

    @Test
    public void get04() {
        //Set the url
        //String url = "https://jsonplaceholder.typicode.com/todos"; //This usage is not recommended.
        // We will put base url into request specification in the base_url package.
        //To be able to reach spec object we need to extend to the related class.

        spec.accept(ContentType.JSON).pathParam("first", "todos");
        //"first" named parameter represents the "todos" parameter in the endpoint.

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].title",equalTo("delectus aut autem"), // --> To check Json value from a list of Jsons first write its index.---> "[0].title"
                        "[1].completed",equalTo(false), // --> To check Json value from a list of Jsons first write its index.---> "[1].completed"
                        "", hasSize(200),  // OR  "title", hasSize(200)
                        "title", hasItem("quis eius est sint explicabo"),
                        "userId", hasItems(2, 7, 9));

        /*
        If response body returns Collection (list):
                i)  check its size by hasSize() method,
                ii) check if an element exists in the collection by hasItem() method,
                iii) check if multiple elements exist by hasItems() method,

         */
    }
}