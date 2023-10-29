package steps;

import data.BookingData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.request.*;

import utils.RequestSpec;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookingSteps {
    RequestSpecification requestSpecification;
    RequestSpecification httpRequest;
    Booking newBooking;
    BookingDates newBookingDates;
    int addedBookingId;


    @Step("create request specification, set authentication and pass it to httpRequest spec")
    public BookingSteps createRequestSpecification() {
        requestSpecification = RequestSpec.getRequestSpecification(BookingData.baseUri, BookingData.basePath)
                .build()
                .auth()
                .preemptive()
                .basic(BookingData.userName, BookingData.password);

        httpRequest = RestAssured.given().spec(requestSpecification);

        return this;
    }

    @Step
    public BookingSteps setNewBookingObject() {
        newBooking = new Booking();
        newBookingDates = new BookingDates();

        newBooking.setFirstname(BookingData.newBookingFirstName);
        newBooking.setLastname(BookingData.newBookingLastName);
        newBooking.setTotalprice(BookingData.newBookingTotalPrice);
        newBooking.setDepositpaid(BookingData.newBookingDepositPaid);

        newBookingDates.setCheckin(BookingData.newBookingCheckin);
        newBookingDates.setCheckout(BookingData.newBookingCheckout);

        newBooking.setBookingdates(newBookingDates);
        newBooking.setAdditionalneeds(BookingData.newBookingAditionalneeds);

        return this;
    }


    @Step
    public BookingSteps createNewBooking() {
        Response response = httpRequest.given().body(newBooking).post();

        addedBookingId = response.then().extract().jsonPath().getInt("bookingid");

        return this;
    }

    @Step()
    public BookingSteps validateAddedBooking() {
        Response response = httpRequest.get("/" + addedBookingId);

        Booking booking = response.as(Booking.class);

        String actualFirstName = booking.getFirstname();
        String actualLastName = booking.getLastname();

        String expectedFirstName = BookingData.newBookingFirstName;
        String expectedLastName = BookingData.newBookingLastName;

        List<String> actualData = List.of(actualFirstName, actualLastName);

        List<String> expectedData = List.of(expectedFirstName, expectedLastName);

        assertThat(actualData, equalTo(expectedData));

        return this;
    }


}
