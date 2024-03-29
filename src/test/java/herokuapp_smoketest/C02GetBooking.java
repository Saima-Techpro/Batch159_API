package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;

import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static herokuapp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02GetBooking extends HerOkuAppBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/2146
    When
         User sends Get request
     Then
         Status Code is 200
     And
         Response body is like : {
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
     */

    @Test
    public void get(){
        spec.pathParams("first", "booking","second" , bookingid);

        //set expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo expectedData = new HerokuPojo("Tom","Hanks",112,true,bookingDates,"Breakfast");
        System.out.println("bookingDates = "+bookingDates);
        System.out.println("expectedData = " +expectedData);

        //Send request and get the response
        Response response =  given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertions
        HerokuPojo actualData = response.as(HerokuPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());



    }
}
