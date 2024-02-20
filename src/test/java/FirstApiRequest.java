import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FirstApiRequest {

    /*
    To make API Request, we need to add Rest Assured dependency to pom.xml
    How to get:
        i) Status code
        ii) Status line
        iii) Content type
        iv) Header
        iii) Response time
     */
    public static void main(String[] args) {

        given().when().get("https://petstore.swagger.io/v2/pet/978").prettyPrint();  // when() is OPTIONAL

        System.out.println("==============================");

        Response response = given().get("https://petstore.swagger.io/v2/pet/978");

        // How to print response on the console
        System.out.println("reference of the response: "+response);  // prints reference of the response

        System.out.println("==============================");

        response.prettyPrint();  // prints actual body

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
        System.out.println("***** Headers One by One ******");
        System.out.println("Header date: " + response.header("Date"));
        System.out.println("Header server: " + response.header("server"));


        System.out.println("=========== Delete it now =================");

//        given().when().delete("https://petstore.swagger.io/v2/pet/8899").prettyPrint();

    }
}
