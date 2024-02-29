package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
public class C05DeleteBooking extends HerOkuAppBaseUrl {
    /*
  Given
      https://restful-booker.herokuapp.com/booking/{{bookingid}}
  When
      send delete request to the url
  Then
      status code is 201
  And
      body: Created
   */
    @Test
    public void delete(){
        spec.pathParams("first","booking"
                ,"second",bookingid);

        String expectedData = "Created";

        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        response.then().statusCode(201);

        String actualData = response.asString();
        assertEquals(expectedData,actualData);
    }
}