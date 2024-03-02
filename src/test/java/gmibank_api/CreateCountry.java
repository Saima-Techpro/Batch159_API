package gmibank_api;

import base_urls.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import pojos.CountryPojo;
import pojos.StatePojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class CreateCountry extends GMIBankBaseUrl {
    /*
    Given
        https://gmibank.com/api/tp-countries
    And
        {
            "id": null,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
    When
        Send post request
    Then
        Status code is 201
    And
        Response body is like:
        {
            "id": 189865,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
     */
    @Test
    public void post(){
        spec.pathParams("first","api","second","tp-countries");

        StatePojo state01 = new StatePojo(0,"Apple",null);
        StatePojo state02 = new StatePojo(1,"Orange",null);
        StatePojo state03 = new StatePojo(2,"Peach",null);
        List<StatePojo> states = new ArrayList<>();
        states.add(state01);
        states.add(state02);
        states.add(state03);
        System.out.println("states = " + states);

        CountryPojo payload = new CountryPojo(null,"Banana Republic",states);
        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}/{second}");
        response.prettyPrint();
        //1st Validation: response.then() method
        //2nd Validation: jsonPath()
        //3rd Validation: as() method
        //4th Validation as() method (By converting response to Map)
        //5th Validation--> Best Practice: Pojo Class + Object Mapper

        CountryPojo actualData = convertJsonToJava(response.asString(), CountryPojo.class);

        assertEquals(payload.getName(),actualData.getName());
        assertEquals(payload.getStates().get(0).getName(),actualData.getStates().get(0).getName());
        assertEquals(payload.getStates().get(0).getId(),actualData.getStates().get(0).getId());
        assertEquals(payload.getStates().get(1).getName(),actualData.getStates().get(1).getName());
        assertEquals(payload.getStates().get(1).getId(),actualData.getStates().get(1).getId());
        assertEquals(payload.getStates().get(2).getName(),actualData.getStates().get(2).getName());
        assertEquals(payload.getStates().get(2).getId(),actualData.getStates().get(2).getId());
    }
}
