import data.BookingData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.Booking;
import models.request.BookingDates;
import org.testng.annotations.Test;
import steps.BookingSteps;

public class ObjectMappingTests {

    BookingSteps bookingSteps = new BookingSteps();

    @Test
    public void objectMappingTest() {
        bookingSteps
                .createRequestSpecification()
                .setNewBookingObject()
                .createNewBooking()
                .validateAddedBooking();
    }

}
