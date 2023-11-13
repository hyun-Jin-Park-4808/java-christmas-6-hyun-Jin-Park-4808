package christmas.main.vo;

import static christmas.constants.Constant.LAST_DATE_OF_CHRISTMAS_EVENT;
import static christmas.constants.Constant.SPECIAL_DAYS;

import christmas.ui.error.ErrorMessage;
import java.util.Objects;

public class ReservationDate {
    public static final int START_DATE = 1;
    public static final int LAST_DATE = 31;
    private final int reservationDate;

    public ReservationDate(int reservationDate) {
        validateRangeOfDate(reservationDate);
        this.reservationDate = reservationDate;
    }

    public int getReservationDate() {
        return this.reservationDate;
    }

    private void validateRangeOfDate(int date) {
        if (isOutOfRange(date)) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_DATE_EXCEPTION);
        }
    }

    private static boolean isOutOfRange(int date) {
        return date < START_DATE || date > LAST_DATE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationDate reservationDateObject = (ReservationDate) o;
        return reservationDate == reservationDateObject.reservationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationDate);
    }

    public boolean isNotDuringTheEventPeriod() {
        return this.reservationDate > LAST_DATE_OF_CHRISTMAS_EVENT;
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(this.reservationDate);
    }

}
