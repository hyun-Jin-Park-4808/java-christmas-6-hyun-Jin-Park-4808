package christmas.main.service;

import christmas.menus.type.Menu;
import christmas.menus.type.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class MainService {

    public static final int MIN_PRICE_FOR_EVENT = 10000;
    public static final int LAST_DATE_OF_CHRISTMAS_EVENT = 25;
    public static final int MIN_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT = 1000;
    public static final int ADDED_DISTCOUNT_AMOUNT_OF_CHRISTMAS_EVENT = 100;
    public static final int YEAR_FOR_EVENT = 2023;
    public static final int MONTH_FOR_EVNET = 12;
    public static final int FRIDATY_NUMBER = 5;
    public static final int SATURDAY_NUMBER = 6;
    public static final int DISCOUNT_AMOUNT_PER_MENU = 2023;

    public static int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> orders) {
        int totalPrice = 0;
        for (Entry<Menu, Integer> order : orders.entrySet()) {
            int priceOfOrdersPerMenu = order.getKey().getPrice();
            int numberOfOrdersPerMenu = order.getValue();
            totalPrice += priceOfOrdersPerMenu * numberOfOrdersPerMenu;
        }
        return totalPrice;
    }

    public static boolean isTheEventApplied(int totalPrice) {
        if (totalPrice >= MIN_PRICE_FOR_EVENT) {
            return true;
        }
        return false;
    }

    public static int calculateDiscountAmountOfChristmasEvent(int date) {
        if (isNotDuringTheEventPeriod(date)) {
            return 0;
        }
        return MIN_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT
                + calculateAddedDiscountAmountOfChristmasEvent(date);
    }

    private static int calculateAddedDiscountAmountOfChristmasEvent(int date) {
        return calculatePassedDaysSinceThe1st(date) * ADDED_DISTCOUNT_AMOUNT_OF_CHRISTMAS_EVENT;
    }

    private static int calculatePassedDaysSinceThe1st(int date) {
        return date - 1;
    }

    private static boolean isNotDuringTheEventPeriod(int date) {
        return date > LAST_DATE_OF_CHRISTMAS_EVENT;
    }

    public static int calculateDiscountAmountOfWeekdayEvent(int date, Map<Menu, Integer> orders) {
        if (!isWeekday(date)) {
            return 0;
        }
        return getNumberOfDesserts(orders) * DISCOUNT_AMOUNT_PER_MENU;
    }

    private static int getNumberOfDesserts(Map<Menu, Integer> orders) {
        return orders.entrySet().stream()
                .filter(entry -> entry.getKey().isSameMenuType(MenuType.DESSERT))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    private static boolean isWeekday(int date) {
        LocalDate localDate = LocalDate.of(YEAR_FOR_EVENT, MONTH_FOR_EVNET, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        if (isNotWeekday(dayOfWeekNumber)) {
            return false;
        }
        return true;
    }

    private static boolean isNotWeekday(int dayOfWeekNumber) {
        return dayOfWeekNumber >= FRIDATY_NUMBER && dayOfWeekNumber <= SATURDAY_NUMBER;
    }

}
