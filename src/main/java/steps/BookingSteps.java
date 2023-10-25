package steps;

import data.BookingUpdateData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;


public class BookingSteps {
    JSONObject tokenRequest = BookingUpdateData.getTokenRequestData();
    JSONObject putRequest = BookingUpdateData.getPutRequestData();
    final String baseUri = "https://restful-booker.herokuapp.com";

    @Step("get token for put request")
    public String getToken() {
        Response tokenResponse = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(tokenRequest.toJSONString())
                .post(baseUri + "/auth");

        String token = tokenResponse.jsonPath().getString("token");

        System.out.println("got token successfully");
        return token;
    }

    @Step("update booking and return response")
    public Response updateBooking(String token) {
        Response putResponse = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + token)
                .body(putRequest.toJSONString())
                .put(baseUri + "/booking/1");

        System.out.println("update booking successfully");
        return putResponse;
    }

    @Step("validate that status code is 201 and log response data")
    public void validateStatusCodeAndLogData(Response response) {
        response
                .then()
                .log()
                .ifStatusCodeIsEqualTo(201) // ეს არ დალოგავს არაფერს, იმიტომ რომ 200ია სტატუს კოდი:)
                .extract()
                .statusCode();
    }

}
