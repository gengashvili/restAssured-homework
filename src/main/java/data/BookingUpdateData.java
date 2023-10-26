package data;

import org.json.simple.JSONObject;

public class BookingUpdateData {

    public static JSONObject getTokenRequestData() {
        JSONObject tokenRequest = new JSONObject();
        tokenRequest.put("username", "admin");
        tokenRequest.put("password", "password123");

        return tokenRequest;
    }

    public static JSONObject getPutRequestData() {
        JSONObject putRequest = new JSONObject();
        putRequest.put("firstname", "giorgi");
        putRequest.put("lastname", "gengashvili");
        putRequest.put("totalprice", 111);
        putRequest.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        putRequest.put("bookingdates", bookingDates);
        putRequest.put("additionalneeds", "Breakfast");

        return putRequest;
    }

}
