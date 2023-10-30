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
