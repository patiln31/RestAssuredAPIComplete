package Day6;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class JsonSchemaValidation {
    @Test(enabled = true, priority = 1)
    public void jsonSchemaValidationTest() {
        given()
                .when()
                .get("http://localhost:3000/store")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschemavalidation.json"));
    }
}
