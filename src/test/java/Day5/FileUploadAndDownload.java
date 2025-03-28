package Day5;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class FileUploadAndDownload {

    @Test(enabled = true, priority = 1)
    public void testSingleFileUpload() {

        File file = new File("E:\\Work\\RestAssuredAPIComplete\\src\\test\\resources\\store.json");
        given()
                .multiPart("file", file)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("fileName", equalTo("store.json"));
    }

    @Test(enabled = false, priority = 2)
    public void testSingleMultipleFileUpload() {

        File file = new File("E:\\Work\\RestAssuredAPIComplete\\src\\test\\resources\\store.json");
        File file2 = new File("E:\\Work\\RestAssuredAPIComplete\\src\\test\\resources\\students.json");
        given()
                .multiPart("files", file)
                .multiPart("files", file2)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("store.json"))
                .body("[1].fileName", equalTo("students.json"));
    }

    @Test(enabled = true, priority = 2)
    public void testFileDownload() {

        File file = new File("E:\\Work\\RestAssuredAPIComplete\\src\\test\\resources\\file1.txt");
        given()
                .multiPart("file", file)
                .contentType("multipart/form-data")
                .when()
                .get("http://localhost:8080/downloadFile/file1.txt")
                .then()
                .statusCode(200)
                .log().body();
    }
}
