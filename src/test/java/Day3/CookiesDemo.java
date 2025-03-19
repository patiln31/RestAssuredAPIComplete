package Day3;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

    @Test(enabled = true,priority = 1)
    public void testCookies(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC","AVcja2fCdxpJ7kQSJIZeQHHHrpu4lqdo38LSc_93i8ai30tqaXLrIJGkGQ")
                .log().all();
    }

    @Test(enabled = true,priority = 2)
    public void getCookiesInfo(){
        Response res = given()
                .when()
                .get("https://www.google.com/");

        String cookie_value = res.getCookie("AEC");
        System.out.println("Value = "+cookie_value);

        Map<String, String> cookie_values = res.getCookies();
        for (String k : cookie_values.keySet()){
            System.out.println(">>>> "+res.getCookie(k));
        }

    }
}
