package data;

import org.testng.annotations.DataProvider;

public class BookingDataProvider {

    @DataProvider
    public Object[][] bookingData() {
        int expectedStatusCode = BookingData.EXPECTED_STATUS_CODE_ON_BOOKING_UPDATE;
        int expectedFirstBookingTotalPrice= BookingData.UPDATE_BOOKING_TOTAL_PRICE;
        int expectedSecondBookingTotalPrice= BookingData.UPDATE_BOOKING_TOTAL_PRICE1;

        return new Object[][] {
                {
                        BookingData.UPDATE_BOOKING_FIRST_NAME,
                        BookingData.UPDATE_BOOKING_LAST_NAME,
                        BookingData.UPDATE_BOOKING_TOTAL_PRICE,
                        BookingData.UPDATE_BOOKING_DEPOSIT_PAID,
                        BookingData.UPDATE_BOOKING_BOOKING_CHECKIN,
                        BookingData.UPDATE_BOOKING_CHECKOUT,
                        BookingData.UPDATE_BOOKING_ADDITIONAL_NEEDS,
                        BookingData.UPDATE_BOOKING_PASSPORT_NO,
                        expectedStatusCode,
                        expectedFirstBookingTotalPrice,
                },
                {
                        BookingData.UPDATE_BOOKING_FIRST_NAME1,
                        BookingData.UPDATE_BOOKING_LAST_NAME1,
                        BookingData.UPDATE_BOOKING_TOTAL_PRICE1,
                        BookingData.UPDATE_BOOKING_DEPOSIT_PAID1,
                        BookingData.UPDATE_BOOKING_BOOKING_CHECKIN1,
                        BookingData.UPDATE_BOOKING_CHECKOUT1,
                        BookingData.UPDATE_BOOKING_ADDITIONAL_NEEDS1,
                        BookingData.UPDATE_BOOKING_PASSPORT_NO,
                        expectedStatusCode,
                        expectedSecondBookingTotalPrice
                }
        };
    }

}
