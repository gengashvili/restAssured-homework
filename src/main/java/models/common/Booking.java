package models.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "firstname",
        "lastname",
        "totalprice",
        "depositpaid",
        "bookingdates",
        "additionalneeds"
})
public class Booking {

    private String
            firstname,
            lastname,
            additionalneeds;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;

}
