import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateTokenTest {
    SoftAssert softAssert;
    // Data provider to read JSON data from a file
    @DataProvider(name = "tokenDataProvider")
    public Object[][] tokenDataProvider() throws IOException {
        // Load JSON file as a String
        String jsonData = new String(Files.readAllBytes(Paths.get("src/test/resources/tokenData.json")));
        return new Object[][]{{jsonData}};
    }

    @Test(dataProvider = "tokenDataProvider")
    public void generateToken(String tokenData) {
        softAssert = new SoftAssert();
        // Base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Request Body
        /*String requestBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";*/

        // Send POST request
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(tokenData)
                .post("/auth");

        // Log the response for debugging
        response.prettyPrint();

        // Assertions
        softAssert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
        softAssert.assertNotNull(response.getBody(), "Response body should not be null");
        softAssert.assertTrue(response.getBody().asString().contains("token"), "Response should contain a token");

        // Extract the token for further use
        String token = response.jsonPath().getString("token");
        softAssert.assertNotNull(token, "Token should not be null");
        System.out.println("Generated Token: " + token);
        softAssert.assertAll();
    }
}
