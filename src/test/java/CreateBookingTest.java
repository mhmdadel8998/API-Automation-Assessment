import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateBookingTest {

    SoftAssert softAssert;

    // Data provider to read JSON data from a file
    @DataProvider(name = "bookingDataProvider")
    public Object[][] bookingDataProvider() throws IOException {
        // Load JSON file as a String
        String jsonData = new String(Files.readAllBytes(Paths.get("src/test/resources/bookingData.json")));
        return new Object[][]{{jsonData}};
    }

    @Test(dataProvider = "bookingDataProvider")
    public void testCreateBooking(String bookingData) {
        softAssert = new SoftAssert();

        // Set the base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";


        // Request payload
        /*String requestBody = "{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";*/

        // Send POST request
        Response response = given()
                .header("Content-Type", "application/json")
                .body(bookingData)
                .when()
                .post()
                .then()
                .statusCode(200) // Validate status code
                .extract()
                .response();

        // Parse response body
        int bookingId = response.jsonPath().getInt("bookingid");
        String firstname = response.jsonPath().getString("booking.firstname");
        String lastname = response.jsonPath().getString("booking.lastname");
        int totalPrice = response.jsonPath().getInt("booking.totalprice");
        boolean depositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        String checkin = response.jsonPath().getString("booking.bookingdates.checkin");
        String checkout = response.jsonPath().getString("booking.bookingdates.checkout");
        String additionalNeeds = response.jsonPath().getString("booking.additionalneeds");


        // Validate response data
        softAssert.assertNotNull(bookingId, "Booking ID should not be null");
        softAssert.assertEquals(firstname, "Jim", "First name mismatch");
        softAssert.assertEquals(lastname, "Brown", "Last name mismatch");
        softAssert.assertEquals(totalPrice, 111, "Total price mismatch");
        softAssert.assertTrue(depositPaid, "Deposit paid mismatch");
        softAssert.assertEquals(checkin, "2018-01-01", "Check-in date mismatch");
        softAssert.assertEquals(checkout, "2019-01-01", "Check-out date mismatch");
        softAssert.assertEquals(additionalNeeds, "Breakfast", "Additional needs mismatch");
        softAssert.assertAll();

        System.out.println("Booking created successfully with ID: " + bookingId);
    }
}
