package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

    @Test(enabled = true,priority = 1)
    public void testQueryAndPathParameters(){

//        https://reqres.in/api/users?page=2&id=5/

        given()
                .pathParams("myPath", "users")      //path params
                .queryParam("page",2)         // query params
                .queryParam("id",5)           // query params
        .when()
                .get("https://reqres.in/api/{myPath}")
        .then()
                .statusCode(200)
                .log().body()
                .log().all();

    }

}
