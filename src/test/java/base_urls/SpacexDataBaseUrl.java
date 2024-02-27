package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class SpacexDataBaseUrl {

    protected RequestSpecification spec;
    @Before //"Before" makes method to be executed before every @Test method
    public void setUp(){
        String baseUrl = " https://api.spacexdata.com/v3";

        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(baseUrl).build();


    }
}