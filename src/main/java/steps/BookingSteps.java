package steps;

import data.BookingData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.common.BookingDates;
import models.request.*;

import models.response.BookingResponse;
import utils.RequestSpec;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class BookingSteps {
    RequestSpecification requestSpecification;
    RequestSpecification httpRequest;
    BookingRequest updateBookingRequest;
    Response updatedBookingResponse;


    @Step("create request specification, set authentication and pass it to httpRequest spec")
    public BookingSteps createRequestSpecification() {
        requestSpecification = RequestSpec.getRequestSpecification(BookingData.BASE_URI, BookingData.BASE_PATH)
                .build()
                .auth()
                .preemptive()
                .basic(BookingData.USERNAME, BookingData.PASSWORD);

        httpRequest = RestAssured.given().spec(requestSpecification);

        return this;
    }

    @Step("set data into update booking request object")
    public BookingSteps setUpdateBookingRequest(
            String firstName,
            String lastName,
            int totalPrice,
            boolean depositPaid,
            String checkIn,
            String checkOut,
            String additionalNeeds,
            Integer passportNo) {
        updateBookingRequest = new BookingRequest();
        BookingDates bookingDates = new BookingDates();

        updateBookingRequest.setFirstName(firstName);
        updateBookingRequest.setLastName(lastName);
        updateBookingRequest.setTotalPrice(totalPrice);
        updateBookingRequest.setDepositPaid(depositPaid);

        bookingDates.setCheckIN(checkIn);
        bookingDates.setCheckOut(checkOut);

        updateBookingRequest.setBookingDates(bookingDates);
        updateBookingRequest.setAdditionalNeeds(additionalNeeds);
        updateBookingRequest.setPassportNo(passportNo);

        return this;
    }

    @Step("update booking")
    public BookingSteps updateBooking() {
        //ჰარდად წამოღებული აიდებით ვერ ეძებდა და ამიტომ მომაქვს გეთ რექვესთით შემდეგ რომ განვაახლო არსებული booking :)
        Response response = httpRequest.get();
        int bookingID = response.jsonPath().getInt("[0].bookingid");

        updatedBookingResponse = httpRequest.body(updateBookingRequest).put("/" + bookingID);

        return this;
    }

    @Step("validate booking update status code and total price field")
    public BookingSteps validateUpdatedBooking(int expectedStatusCode, int expectedBookingTotalPrice) {
        updatedBookingResponse.then().assertThat().statusCode(expectedStatusCode);

        BookingResponse bookingResponse = updatedBookingResponse.as(BookingResponse.class);

        int actualTotalPrice = bookingResponse.getTotalPrice();

        assertThat(actualTotalPrice,equalTo(expectedBookingTotalPrice));

        return this;
    }


}
