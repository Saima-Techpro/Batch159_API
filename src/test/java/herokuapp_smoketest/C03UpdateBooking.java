package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static herokuapp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C03UpdateBooking extends HerOkuAppBaseUrl {
    /*

    Given
        https://restful-booker.herokuapp.com/booking/{{bookingid}}
    And
        body: {
                "firstname" : "Tom",
                "lastname" : "Hanks",
                "totalprice" : 113,
                "depositpaid" : false,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Lunch"
                }
    When
        User sends PUT request
    Then
        Status code is 200
    And
        body: {
                "bookingid": 2146,
                "booking": {
                    "firstname": "Tom",
                    "lastname": "Hanks",
                    "totalprice": 113,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Lunch"
                }
            }

     */
    @Test
    public void put(){
        spec.pathParams("first","booking"
                ,"second", bookingid);

        BookingDatesPojo booking = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo payLoad = new HerokuPojo("Tom","Hanks",113,false,booking,"Lunch");
        System.out.println(payLoad);

//        Response response = given(spec).header("Cookie", "token=e29fe5e20f220c3").body(payLoad).when().put("{first}/{second}");
//        Add token in the header in baseurl class to make it available for all API requests

        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();


        HerokuPojo actualData = response.as(HerokuPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(payLoad.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(booking.getCheckout(),actualData.getBookingdates().getCheckout());


    }
}