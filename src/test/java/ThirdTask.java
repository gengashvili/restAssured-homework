import org.testng.annotations.Test;
import steps.*;

public class ThirdTask {

    BookingSteps bookingSteps = new BookingSteps();
    BooksSteps booksSteps = new BooksSteps();

    @Test()
    public void testBookingDelete() {
        bookingSteps
                .createRequestSpecification()
                .deleteBooking()
                .validateResponseCode();
    }

    @Test
    public void testBooksPagesCountAndAuthors() {
        booksSteps
                .createRequestSpecification()
                .extractResponse()
                .validateBooksPagesCount()
                .validateBooksAuthors();
    }

}