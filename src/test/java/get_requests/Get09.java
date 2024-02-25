package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Get09 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/51
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */

    @Test
    public void get09() {
        //Set the url
        spec.pathParams("first", "booking", "second", 51);

        //Set the expected data
        // start with inner map
        Map<String, String> bookingMap = bookingDatesMapper("2018-01-01", "2019-01-01");
        System.out.println("bookingMap = " + bookingMap);

        // Then outer map
        Map<String, Object> expectedData = herokuAppMapper("John", "Smith", 111, true,bookingMap, "Extra pillows please" );
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}"); // Serialization
        response.prettyPrint();

        //Do assertion

        // De-serialisation
        Map<String, Object>  actualData = response.as(HashMap.class); // De-Serialization

//        Object obj = new HashMap<>();
//        (Map)obj.get();

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
//        assertEquals(  ((Map)(expectedData.get("bookingMap"))).get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(  bookingMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
//        assertEquals(  ((Map)(expectedData.get("bookingMap"))).get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(  bookingMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        // OR you can also use JsonPath
        JsonPath jsonPath = response.jsonPath();
        assertEquals(bookingMap.get("checkin"), jsonPath.getString("bookingdates.checkin"));
        assertEquals(bookingMap.get("checkout"), jsonPath.getString("bookingdates.checkout"));
    }
}
