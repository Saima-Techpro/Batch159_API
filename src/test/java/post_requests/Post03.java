package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Post03 extends HerOkuAppBaseUrl {

    /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 999,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2024-01-01",
                "checkout" : "2024-01-01"
            },
            "additionalneeds" : "Extra pillows please"
        }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                        "bookingid": 4809,
                                        "booking": {
                                            "firstname": "Jim",
                                            "lastname": "Brown",
                                            "totalprice": 999,
                                            "depositpaid": true,
                                            "bookingdates": {
                                                "checkin": "2024-01-01",
                                                "checkout": "2024-01-01"
                                            },
                                            "additionalneeds": "Extra pillows please"
                                        }
                                    }
 */

    @Test
    public void post03(){

        // Set the url
        spec.pathParam("first", "booking");
        // Set the expected data
        Map<String, String> bookingDatesMap = bookingDatesMapper("2024-01-01", "2024-01-01");
        Map<String, Object> payload = herokuAppMapper("Jim", "Brown", 999, true, bookingDatesMap, "Extra pillows please");
        System.out.println("payload = " + payload);

        // Send the request and get the response
        Response response = given(spec).when().body(payload).post("{first}"); // serialisation
        response.prettyPrint();

        // Do assertions
        response.then()
                .statusCode(200)
                .body("booking.firstname",equalTo(payload.get("firstname"))
                        ,"booking.lastname",equalTo(payload.get("lastname"))
                        ,"booking.totalprice",equalTo(payload.get("totalprice"))
                        ,"booking.depositpaid",equalTo(payload.get("depositpaid"))
                        ,"booking.bookingdates.checkin",equalTo(bookingDatesMap.get("checkin"))
                        ,"booking.bookingdates.checkout",equalTo(bookingDatesMap.get("checkout"))
                        ,"booking.additionalneeds",equalTo(payload.get("additionalneeds")));

        // You can also use JsonPath() for assertions

        // De-serialisation

        Map<String ,Object> actualData = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(200, response.statusCode());
        assertEquals(payload.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(payload.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(payload.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(payload.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(payload.get("additionalneeds"), ((Map) actualData.get("booking")).get("additionalneeds"));








    }
}
