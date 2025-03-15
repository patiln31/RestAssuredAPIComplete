package Day2;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DiffWaysToCreatePostRequests {

    @Test(priority = 1)
    void testPostUsingHashMap() {

        HashMap data = new HashMap<>();

        data.put("id", "4");
        data.put("name", "Nile");
        data.put("location", "BLR");
        data.put("phone", "1234569870");

        String[] course = {"C", "C++", "GLang"};
        data.put("courses", course);

        given()
                    .contentType("application/json")
                    .body(data)
                .when()
                    .post("http://localhost:3000/students")
                .then()
                    .statusCode(201)
                    .body("name", equalTo("Nile"))
                    .body("location", equalTo("BLR"))
                    .body("phone", equalTo("1234569870"))
                    .body("courses[0]", equalTo("C"))
                    .body("courses[1]", equalTo("C++"))
                    .body("courses[2]", equalTo("GLang"))
                    .header("Content-Type", "application/json")
                    .log().all();

    }

    @Test(priority = 2)
    void testDelete() {

        given()
                .when()
                    .delete("http://localhost:3000/students/4")
                .then()
                    .statusCode(200);

    }
}
