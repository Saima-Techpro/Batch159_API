import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FirstApiRequest {
    public static void main(String[] args) {

        given().when().get("https://petstore.swagger.io/v2/pet/8899").prettyPrint();

        System.out.println("==============================");

        Response response = given().get("https://petstore.swagger.io/v2/pet/8899");

        // How to print response on the console
        System.out.println(response);  // prints reference of the response

        System.out.println("==============================");

        response.prettyPrint();

        // How to get status code
        System.out.println("Status Code: "  + response.statusCode());


        // How to get status line
        System.out.println("Status Line: "  + response.statusLine());

        // How to get content Type
        System.out.println("Content Type: "  + response.contentType());

        // How to get response time
        System.out.println("response time: "  + response.time());


        // How to get all headers
        System.out.println("***** All Headers ******");
        System.out.println(response.headers());


        // How to get headers one by one
        System.out.println("Header date: " + response.header("Date"));
        System.out.println("Header server: " + response.header("server"));


        System.out.println("=========== Delete it now =================");

        given().when().delete("https://petstore.swagger.io/v2/pet/8899").prettyPrint();

    }
}
