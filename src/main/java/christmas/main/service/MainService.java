package christmas.main.service;

import christmas.menus.type.Menu;
import java.util.Map;

public class MainService {

    public static final int MIN_PRICE_FOR_EVENT = 10000;
    public static final int LAST_DATE_OF_CHRISTMAS_EVENT = 25;
    public static final int MIN_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT = 1000;
    public static final int ADDED_DISTCOUNT_AMOUNT_OF_CHRISTMAS_EVENT = 100;

    public static int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> orders) {
        int totalPrice = 0;
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
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
}
