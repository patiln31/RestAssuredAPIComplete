package Day5;
import static org.hamcrest.Matchers.*;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;

public class ParsingXMLResponse {


    @Test
    public void testXMLResponse(){

        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .statusCode(200);

    }

    @Test
    public void testMultipleXMLResponse(){

        Response  response = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");


        XmlPath xmlObj = new XmlPath(response.asString());

        List<String> list = xmlObj.getList("travelNode.travelchildnode.child");
        



    }

}
