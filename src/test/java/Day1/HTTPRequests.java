package Day1;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class HTTPRequests {
    int id;

    @Test(enabled = true, priority = 1)
    void getUsers() {

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test(enabled = true, priority = 2)
    void createUser() {

        HashMap data = new HashMap();
        data.put("name", "Nile");
        data.put("job", "QA");

       id = given()
                    .contentType("application/json")
                    .body(data)
                .when()
                    .post("https://reqres.in/api/users").jsonPath().getInt("id");
//                .then()
//                    .statusCode(201)
//                    .log().all();
    }

    @Test(enabled = true, priority = 3, dependsOnMethods = "createUser")
    void updateUser() {

        HashMap data = new HashMap();
        data.put("name", "Nilesh");
        data.put("job", "QA Automation");

            given()
                    .contentType("application/json")
                    .body(data)
                .when()
                    .put("https://reqres.in/api/users/"+id)
                .then()
                    .statusCode(200)
                    .log().all();
    }

    @Test(enabled = true, priority = 4)
    void deleteUser(){

        given()
                .when()
                    .delete("https://reqres.in/api/users/"+id)
                .then()
                    .statusCode(204)
                    .log().all();
    }
}
