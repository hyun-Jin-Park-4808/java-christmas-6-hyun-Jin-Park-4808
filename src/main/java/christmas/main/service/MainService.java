package christmas.main.service;

import static christmas.constants.Constant.*;

import christmas.menus.type.Menu;
import christmas.menus.type.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class MainService {



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
        return calculatePassedDaysSinceThe1st(date) * ADDED_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT;
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
        return getNumberOfMenu(orders, MenuType.DESSERT) * DISCOUNT_AMOUNT_PER_MENU;
    }

    public static int calculateDiscountAmountOfWeekendEvent(int date, Map<Menu, Integer> orders) {
        if (isWeekday(date)) {
            return 0;
        }
        return getNumberOfMenu(orders, MenuType.MAIN) * DISCOUNT_AMOUNT_PER_MENU;
    }

    private static int getNumberOfMenu(Map<Menu, Integer> orders, MenuType menuType) {
        return orders.entrySet().stream()
                .filter(entry -> entry.getKey().isSameMenuType(menuType))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    private static boolean isWeekday(int date) {
        LocalDate localDate = LocalDate.of(YEAR_FOR_EVENT, MONTH_FOR_EVENT, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        if (isNotWeekday(dayOfWeekNumber)) {
            return false;
        }
        return true;
    }

    private static boolean isNotWeekday(int dayOfWeekNumber) {
        return dayOfWeekNumber >= FRIDAY_NUMBER && dayOfWeekNumber <= SATURDAY_NUMBER;
    }

    public static int checkAvailabilityForSpecialEvent(int date) {
        if (SPECIAL_DAYS.contains(date)) {
            return DISCOUNT_AMOUNT_OF_SPECIAL_EVENT;
        }
        return 0;
    }

    public static int checkAvailabilityForGiveawayEvent(int totalPriceBeforeDiscount) {
        if (isApplicableToGiveawayEvent(totalPriceBeforeDiscount)) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private static boolean isApplicableToGiveawayEvent(int totalPriceBeforeDiscount) {
        return totalPriceBeforeDiscount >= STANDARD_PRICE_FOR_GIVEAWAY_EVENT;
    }
}
