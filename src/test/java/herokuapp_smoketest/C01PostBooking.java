package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C01PostBooking extends HerOkuAppBaseUrl {
    public static int bookingid;
    /*

    Given
        https://restful-booker.herokuapp.com/booking
    And
        body: {
                "firstname" : "Tom",
                "lastname" : "Hanks",
                "totalprice" : 112,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
                }
    When
        User sends post request
    Then
        Statuscode is 200
    And
        body: {
                "bookingid": 2146,
                "booking": {
                    "firstname": "Tom",
                    "lastname": "Hanks",
                    "totalprice": 112,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
            }

     */
    @Test
    public void post(){
        spec.pathParam("first", "booking");

        BookingDatesPojo bookingMap = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo payLoad = new HerokuPojo("Tom","Hanks",112,true,bookingMap,"Breakfast");
        System.out.println(payLoad);

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerokuResponsePojo actualData = response.as(HerokuResponsePojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(payLoad.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingMap.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

        bookingid = response.jsonPath().getInt("bookingid");
        System.out.println("bookingId = " + bookingid);

    }
}