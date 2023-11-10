package christmas.ui;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.menus.service.MenuService.getAllMenus;

import christmas.main.vo.ReservationDate;
import christmas.ui.error.ErrorMessage;
import java.util.regex.Pattern;

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

    public static String[] splitUserInputForOrder(String userInputForOrder) {
        validateFormatOfInputForOrder(userInputForOrder);
        String[] menus = userInputForOrder.split(",|-");
        validateExistenceOfOrderMenu(menus, getAllMenus());
        return menus;
    }

    private static void validateExistenceOfOrderMenu(String[] menus, String allMenus) {
        for (int i = 0; i < menus.length / 2; i++) {
            validateInclusion(isIncluded(menus, allMenus, i));
        }
    }

    private static void validateInclusion(boolean isIncluded) {
        if (!isIncluded) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_ORDER_EXCEPTION);
        }
    }

    private static boolean isIncluded(String[] menus, String allMenus, int i) {
        return allMenus.contains(menus[i * 2]);
    }



    private static void validateFormatOfInputForOrder(String userInputForOrder) {
        String formatOfOrder = "^[가-힣]+-[1-20]$";
        for (String menu : userInputForOrder.split(",")) {
            if (!Pattern.matches(formatOfOrder, menu)) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_ORDER_EXCEPTION);
            }
        }
    }
}
