import data.BookingDataProvider;
import org.testng.annotations.Test;
import steps.BookingSteps;

public class FifthTask {

    BookingSteps bookingSteps = new BookingSteps();

    @Test(dataProvider = "bookingData", dataProviderClass = BookingDataProvider.class)
    public void updateBooking(
        String firstName,
        String lastName,
        int totalPrice,
        boolean depositPaid,
        String checkIn,
        String checkOut,
        String additionalNeeds,
        Integer passportNo,
        int expectedStatusCode,
        int expectedBookingTotalPrice
    )
    {

        bookingSteps
                .createRequestSpecification()
                .setUpdateBookingRequest(
                        firstName,
                        lastName,
                        totalPrice,
                        depositPaid,
                        checkIn, checkOut,
                        additionalNeeds,
                        passportNo)
                .updateBooking()
                .validateUpdatedBooking(expectedStatusCode, expectedBookingTotalPrice);

    }


}
