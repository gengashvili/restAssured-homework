package models.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"checkin", "checkout"})
public class BookingDates {

    private String checkin, checkout;

}
