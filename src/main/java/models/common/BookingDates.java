package models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"checkin", "checkout"})
public class BookingDates {

    @JsonProperty("checkin")
    private String checkIN;

    @JsonProperty("checkout")
    private String checkOut;

}
