package steps;

import data.BookingData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RequestSpec;

public class BookingSteps {

    RequestSpecification requestSpecification;
    RequestSpecification httpRequest;
    Response deleteResponse;

    @Step("create request specification, set authentication and pass it to httpRequest spec")
    public BookingSteps createRequestSpecification() {
        requestSpecification = RequestSpec.getRequestSpecification(BookingData.baseUri, BookingData.basePath).build()
                .auth()
                .preemptive()
                .basic(BookingData.userName, BookingData.password);

        httpRequest = RestAssured.given().spec(requestSpecification);

        return this;
    }

    @Step("get existing booking list and delete first one")
    public BookingSteps deleteBooking() {
        Response bookingIdResponse = httpRequest.get();

        int doomedBookingID = bookingIdResponse.then().extract().jsonPath().getInt("[0].bookingid");

        deleteResponse = httpRequest.delete("/" + doomedBookingID);

        return this;
    }

    @Step("validate that response code is validate that response code is successful")
    public BookingSteps validateResponseCode() {
        deleteResponse.then().assertThat().statusCode(201);

        System.out.println("booking deleted successfully and status line is: " + deleteResponse.getStatusLine() + ".");
        return this;
    }

}
