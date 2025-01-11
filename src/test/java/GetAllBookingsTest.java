
    import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
    import org.testng.asserts.SoftAssert;

    import java.util.List;

    public class GetAllBookingsTest {
        SoftAssert softAssert;

        @Test
        public void testGetAllBookings() {
            softAssert = new SoftAssert();
            // Base URI
            RestAssured.baseURI = "https://restful-booker.herokuapp.com";

            // Perform GET request
            Response response = RestAssured
                    .given()
                    .when()
                    .get("/booking")
                    .then()
                    .statusCode(200) // Assert that the response status code is 200
                    .extract()
                    .response();

            // Retrieve the list of bookings
            List<?> bookings = response.jsonPath().getList("bookingid");

            // Print the list of bookings for debugging purposes
            System.out.println("List of bookings: " + bookings);

            // Assert that the list is greater than zero
            softAssert.assertTrue(bookings.size() > 0, "The list of bookings is not greater than zero.");
        }
    }


