import data.BookingDataProvider;
import io.qameta.allure.*;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import steps.BookingSteps;

@Epic("Rest Assured fifth task")
public class FifthTask {

    BookingSteps bookingSteps = new BookingSteps();

    @Test(dataProvider = "bookingData", dataProviderClass = BookingDataProvider.class)
    @Description("update booking using pojo classes and validate updated booking")
    @Severity(SeverityLevel.BLOCKER)
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
