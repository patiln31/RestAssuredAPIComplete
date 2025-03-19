package Day2;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DiffWaysToCreatePostRequests {


    // Multiple ways of handling post requests (all the approaches)

    // 1. Post request using HashMap
    // 2. Post request using org.json library
    // 3. Post request using POJO Class
    // 4. Post request using External Json File

    // 1. Post request using HashMap
    @Test(enabled = false, priority = 1)
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

    // 2. Post request using org.json library

    @Test(enabled = false, priority = 1)
    void testPostUsingJsonLibrary() {

//        JsonObject data = new JsonObject();
        JSONObject data = new JSONObject();

        data.put("id", "4");
        data.put("name", "Nile");
        data.put("location", "BLR");
        data.put("phone", "1234569870");

        String[] course = {"C", "C++", "GLang"};
        data.put("courses", course);

        given()
                .contentType("application/json")
                .body(data.toString())
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


    // 3. Post request using POJO Class

    @Test(enabled = false, priority = 1)
    void testPostUsingPojoClass() throws JsonProcessingException {

        Pojo_PostReq data = new Pojo_PostReq();

        data.setId("4");
        data.setName("Nile");
        data.setLocation("BLR");
        data.setPhone("1234569870");

        String courses[] = {"C", "C++", "GLang"};
        data.setCourses(courses);

        // Correct Serialization:
        //If you have a JSONObject, you should use its toString() method to get a JSON string.
        //If you have a Java object (POJO), you can use ObjectMapper to serialize it to a JSON string.
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);

        System.out.println("JSON Data: " + json); // Print the JSON data

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
                .log().body()
                .log().all();

    }

    // 4. Post request using External Json File

    @Test(enabled = true, priority = 1)
    void testPostUsingExternalJsonFile() throws JsonProcessingException, FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())
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
                .log().body()
                .log().all();

    }



    // Deleting the current data for re runs
    @Test(enabled = true, priority = 2)
    void testDelete() {

        given()
                .when()
                .delete("http://localhost:3000/students/4")
                .then()
                .statusCode(200);

    }


}
