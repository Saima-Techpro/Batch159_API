package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerokuApp {

//    public static void main(String[] args) {
//        System.out.println("Token = " + generateToken());
//    }

    public static String generateToken(){
        String credentials = "{\"username\" : \"admin\",\"password\" : \"password123\"}";
        Response response = given().body(credentials).contentType(ContentType.JSON).when().post("https://restful-booker.herokuapp.com/auth");
        response.prettyPrint();
        return response.jsonPath().getString("token");
    }
}
