package steps;

import data.BookingData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.common.BookingDates;
import models.request.*;

import models.response.*;
import utils.RequestSpec;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;

public class BookingSteps {
    RequestSpecification requestSpecification;
    RequestSpecification httpRequest;
    BookingRequest bookingRequest;
    BookingDates newBookingDates;
    CreatedBookingResponse createdBookingResponse;
    BookingResponse bookingResponse;



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
        bookingRequest = new BookingRequest();
        newBookingDates = new BookingDates();

        bookingRequest.setFirstname(BookingData.newBookingFirstName);
        bookingRequest.setLastname(BookingData.newBookingLastName);
        bookingRequest.setTotalprice(BookingData.newBookingTotalPrice);
        bookingRequest.setDepositpaid(BookingData.newBookingDepositPaid);

        newBookingDates.setCheckin(BookingData.newBookingCheckin);
        newBookingDates.setCheckout(BookingData.newBookingCheckout);

        bookingRequest.setAdditionalneeds(BookingData.newBookingAditionalneeds);
        bookingRequest.setBookingdates(newBookingDates);

        return this;
    }


    @Step
    public BookingSteps createNewBooking() {
        Response response = httpRequest.given().body(bookingRequest).post();

        createdBookingResponse = response.as(CreatedBookingResponse.class);

        return this;
    }

    @Step()
    public BookingSteps validateAddedBooking() {
        Response response = httpRequest.get("/" + createdBookingResponse.getBookingid());

        bookingResponse = response.as(BookingResponse.class);

        String actualFirstName = bookingResponse.getFirstname();
        String actualLastName = bookingResponse.getLastname();

        String expectedFirstName = BookingData.newBookingFirstName;
        String expectedLastName = BookingData.newBookingLastName;

        List<String> actualData = List.of(actualFirstName, actualLastName);

        List<String> expectedData = List.of(expectedFirstName, expectedLastName);

        assertThat(actualData, equalTo(expectedData));

        return this;
    }


}
