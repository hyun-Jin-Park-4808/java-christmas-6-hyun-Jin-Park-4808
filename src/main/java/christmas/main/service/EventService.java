package christmas.main.service;

import static christmas.constants.Constant.*;

import christmas.main.vo.ReservationDate;
import christmas.menus.type.Menu;
import christmas.menus.type.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class EventService {


    public static int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> orders) {
        int totalPrice = 0;
        for (Entry<Menu, Integer> order : orders.entrySet()) {
            int priceOfOrdersPerMenu = order.getKey().getPrice();
            int numberOfOrdersPerMenu = order.getValue();
            totalPrice += priceOfOrdersPerMenu * numberOfOrdersPerMenu;
        }
        return totalPrice;
    }

    public static boolean isTheEventApplied(int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount >= MIN_PRICE_FOR_EVENT) {
            return true;
        }
        return false;
    }

    public static int calculateDiscountAmountOfChristmasEvent(ReservationDate date) {
        if (date.isNotDuringTheEventPeriod()) {
            return 0;
        }
        return MIN_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT
                + calculateAddedDiscountAmountOfChristmasEvent(date);
    }

    private static int calculateAddedDiscountAmountOfChristmasEvent(ReservationDate date) {
        return calculatePassedDaysSinceThe1st(date) * ADDED_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT;
    }

    private static int calculatePassedDaysSinceThe1st(ReservationDate date) {
        return date.getReservationDate() - 1;
    }

    public static int calculateDiscountAmountOfWeekdayEvent(ReservationDate date, Map<Menu, Integer> orders) {
        if (!isWeekday(date)) {
            return 0;
        }
        return getNumberOfMenu(orders, MenuType.DESSERT) * DISCOUNT_AMOUNT_PER_MENU;
    }

    public static int calculateDiscountAmountOfWeekendEvent(ReservationDate date, Map<Menu, Integer> orders) {
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

    private static boolean isWeekday(ReservationDate date) {
        LocalDate localDate = LocalDate.of(YEAR_FOR_EVENT, MONTH_FOR_EVENT, date.getReservationDate());
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

    public static int checkAvailabilityForSpecialEvent(ReservationDate date) {
        if (date.isSpecialDay()) {
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

    public static boolean isApplicableToGiveawayEvent(int totalPriceBeforeDiscount) {
        return totalPriceBeforeDiscount >= STANDARD_PRICE_FOR_GIVEAWAY_EVENT;
    }
}
