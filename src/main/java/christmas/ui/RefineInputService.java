package christmas.ui;

import static christmas.constants.Constant.MAX_NUMBER_OF_ORDER;
import static christmas.menus.service.MenuService.getAllMenus;

import christmas.main.vo.ReservationDate;
import christmas.menus.type.Menu;
import christmas.menus.type.MenuType;
import christmas.ui.error.ErrorMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class RefineInputService {

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
        return allMenus.contains(getMenu(menus, i));
    }

    private static void validateFormatOfInputForOrder(String userInputForOrder) {
        String formatOfOrder = "^[가-힣]+-\\d+$";
        for (String menu : userInputForOrder.split(",")) {
            if (!Pattern.matches(formatOfOrder, menu)) {
                throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_ORDER_EXCEPTION);
            }
        }
    }

    public static Map<Menu, Integer> saveOrdersByFoodTypeAndQuantity(String[] menus) {
        Map<Menu, Integer> orders = new HashMap<>();
        validateDuplication(menus);

        for (int i = 0; i < menus.length / 2; i++) {
            Menu menu = Menu.compareSameName(getMenu(menus, i));
            orders.put(menu, Integer.parseInt(getNumberOfMenu(menus, i)));
        }
        validateNumbersExceptForDrink(orders);
        validateTotalCountsOfOrders(orders);
        return orders;
    }

    private static void validateTotalCountsOfOrders(Map<Menu, Integer> orders) {
        int totalCountOfOrders = orders.values().stream().mapToInt(Integer::intValue).sum();
        if (totalCountOfOrders > MAX_NUMBER_OF_ORDER) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_ORDER_EXCEPTION);
        }
    }

    private static void validateNumbersExceptForDrink(Map<Menu, Integer> orders) {
        if (getNumbersExceptForDrink(orders) == 0L) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_ORDER_EXCEPTION);
        }
    }

    private static Long getNumbersExceptForDrink(Map<Menu, Integer> orders) {
        return Optional.ofNullable(orders).map(orderMap -> orderMap.entrySet().stream()
                .filter(entry
                        -> !entry.getKey().isSameMenuType(MenuType.DRINK)).count()).orElse(0L);
    }

    private static String getNumberOfMenu(String[] menus, int i) {
        return menus[(i * 2) + 1];
    }

    private static String getMenu(String[] menus, int i) {
        return menus[i * 2];
    }

    private static void validateDuplication(String[] menus) {
        List<String> menuNames = Arrays.stream(menus)
                .filter(menu -> !Character.isDigit(menu.charAt(0))).toList();

        Set<String> menuNamesExceptForOverlapping = new HashSet<>(menuNames);
        if (menuNamesExceptForOverlapping.size() != menuNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_FORMAT_OF_ORDER_EXCEPTION);
        }
    }
}
