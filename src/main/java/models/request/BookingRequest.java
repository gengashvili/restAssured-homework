package models.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.common.Booking;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"saleprice"})
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest extends Booking{

    @JsonProperty("saleprice")
    private int salePrice;

    @JsonProperty("passportNo")
    private Integer passportNo;

}
