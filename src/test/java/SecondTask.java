import io.restassured.response.Response;
import org.testng.annotations.Test;
import steps.BookingSteps;
import steps.BooksSteps;


public class SecondTask {
    BookingSteps bookingSteps = new BookingSteps();
    BooksSteps booksSteps = new BooksSteps();

    @Test()
    public void testBookingUpdate() {
        String token = bookingSteps.getToken();
        Response response = bookingSteps.updateBooking(token);
        bookingSteps.validateStatusCodeAndLogData(response);
    }

    @Test
    public void testBooksWithHamcrest() {
        Response response = booksSteps.getResponse();
        booksSteps.validateBookPagesCount(response);
        booksSteps.validateBookAuthors(response);
    }

}
