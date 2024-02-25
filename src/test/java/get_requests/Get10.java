package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/41
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Josh",
                "lastname": "Allen",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "midnight snack"
            }
     */
     @Test
     public void get() {
         // Set Url
         spec.pathParams("first", "booking"
                 , "second", 41);
         // Set Expected Data:
         BookingDatesPojo bookinDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
         System.out.println("bookinDates = " + bookinDates);

         HerokuPojo expectedData = new HerokuPojo("Josh", "Allen", 111, true, bookinDates, "midnight snack");
         System.out.println("expectedData = " + expectedData);

         // Send Request And Get the Response:
         Response response = given(spec).when().get("{first}/{second}");  // Serialisation
         response.prettyPrint();

         // Do Assertions:
         HerokuPojo actualData = response.as(HerokuPojo.class);  // De-Serialisation
         assertEquals(200, response.statusCode());
         assertEquals(expectedData.getFirstname(), actualData.getFirstname());
         assertEquals(expectedData.getLastname(), actualData.getLastname());
         assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
         assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
//         assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
         assertEquals(bookinDates.getCheckin(), actualData.getBookingdates().getCheckin());
         assertEquals(bookinDates.getCheckout(), actualData.getBookingdates().getCheckout());
         assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
     }
}
