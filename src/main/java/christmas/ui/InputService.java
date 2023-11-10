package christmas.ui;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.main.vo.ReservationDate;
import christmas.ui.error.ErrorMessage;

public class InputService {


    public static String userInput() {
        return readLine();
    }

    public static ReservationDate parseUserInputToReservationDate(String userInputForDate) {
        return new ReservationDate(validateInputIsANumber(userInputForDate));
    }

    private static int validateInputIsANumber(String userInputForDate) {
        try {
            int reservationDate = Integer.parseInt(userInputForDate);
            return reservationDate;
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_DATE_EXCEPTION);
        }

    }


}
