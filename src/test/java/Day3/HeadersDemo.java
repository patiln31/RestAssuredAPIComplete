package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HeadersDemo {

    @Test(enabled = false,priority = 1)
    public void testHeaders(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .header("Content-Encoding","gzip")
                .header("Server","gws")
                .log().all();
    }

    @Test(enabled = false,priority = 1)
    public void testAllHeadersInfo(){
        Response response = given()
                .when()
                .get("https://www.google.com/");

        Headers headers = response.getHeaders();
        for (Header h : headers){
            System.out.println(h.getName()+" = "+h.getValue());
        }
    }

    @Test(enabled = true,priority = 1)
    public void test1(){
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>> "+response.path("data[3].last_name"));
    }

}
