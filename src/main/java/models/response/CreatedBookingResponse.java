package models.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import models.common.Booking;

@Getter
@Setter
@JsonPropertyOrder({"bookingid", "booking"})
public class CreatedBookingResponse {
    private int bookingid;
    private Booking booking;
}
