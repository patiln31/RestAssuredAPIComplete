package Day4;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJSONResponseData {


    // 1. Approach 1
    @Test(enabled = false, priority = 1)
    public void testJsonResponse() {
        given()
                .when()
                .get("http://localhost:3000/store")
                .then()
                .statusCode(200)
                .body("book[2].title", equalTo("The Quantum World"))
                .log().all();
    }

    // 2. Approach 2
    @Test(enabled = false, priority = 2)
    public void testJsonResponseWithMultipleVerifications() {
        Response response = given()
                .when()
                .get("http://localhost:3000/store");

        Assert.assertEquals(response.getStatusCode(), 200);
        String book = response.jsonPath().get("book[2].title").toString();
        Assert.assertEquals(book, "The Quantum World");

    }

    // 3. Approach 3
    @Test(enabled = true, priority = 2)
    public void testJsonResponseBodyLoopAllTitles() {
        Response response = given()
                .when()
                .get("http://localhost:3000/store");

        JSONObject jo = new JSONObject(response.asString());

        for (int i = 0; i < jo.getJSONArray("book").length(); i++){
            String title = jo.getJSONArray("book").getJSONObject(i).get("title").toString();

            if (title.equals("Ancient Civilizations")){
                System.out.println(">>>> "+title);
                break;
            }
        }


//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        String book = response.jsonPath().get("book[2].title").toString();
//        Assert.assertEquals(book, "The Quantum World");


    }


}
