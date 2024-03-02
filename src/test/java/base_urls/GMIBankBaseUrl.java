package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationGMIBank.generateToken;

public class GMIBankBaseUrl {
    protected RequestSpecification spec;
    @Before  // This method will work before @Test methods
    public void setUp(){
        String baseUrl = "https://gmibank.com";
        spec = new RequestSpecBuilder().
                addHeader("Authorization", "Bearer "+ generateToken())
                .setContentType(ContentType.JSON).
                setBaseUri(baseUrl).
                build();

    }
}
