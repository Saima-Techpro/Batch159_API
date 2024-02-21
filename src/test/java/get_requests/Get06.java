package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
        /*
        Given
            https://restful-booker.herokuapp.com/booking/974
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                        {
                            "firstname": "John",
                            "lastname": "Smith",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }
     */

    @Test
    public void get06() {
        //Set the url
        spec.pathParams("first", "booking", "second", 204);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");  // "{}/{}/{}"
        response.prettyPrint();

        //Do assertion
        //1st Way:
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Breakfast"));

        // we can also use is() method from hamcrest matchers
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",is("John")
                        ,"lastname",is("Smith")
                        ,"totalprice",is(111)
                        ,"depositpaid",is(true)
                        ,"bookingdates.checkin",is("2018-01-01")
                        ,"bookingdates.checkout", is("2019-01-01")
                        ,"additionalneeds",is("Breakfast"));

        /* NOTE:  We have multiple ways to extract the data outside the response body
                  i) asString() => changes to String data type
                  ii) JsonPath
                  iii) Map
                  iv) Pojo class
         */


        //2nd Way: We will use JsonPath class extract the data outside the response body
        //Create JsonPath Object
        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("firstname"); // we can get the data out of response body and store it in String and do String Manipulation
        System.out.println("firstName = " + firstName);

        //Get the data
        assertEquals("John", jsonPath.getString("firstname"));
        assertEquals("Smith", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

        // HomeWork :
        // Do Assertion with soft Assertion





        //Soft Assertion --> Test NG soft assertion
        //To do soft assert follow these 3 steps:
        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        //2nd step: Do assertion by softAssert object
        softAssert.assertEquals(jsonPath.getString("firstname"),"John","firstname did not match");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith","lastname did not match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice did not match");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"),"depositpaid did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","checkin did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","checkout did not match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds did not match");

        //3rd step: Use assertAll() method
        softAssert.assertAll();

    }
}