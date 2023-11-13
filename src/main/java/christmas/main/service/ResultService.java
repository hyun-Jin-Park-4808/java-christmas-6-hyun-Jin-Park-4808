package christmas.main.service;

import static christmas.main.service.EventService.*;

import christmas.main.type.EventType;
import christmas.menus.type.Menu;
import java.util.HashMap;
import java.util.Map;

public class ResultService {
    public static Map<EventType, Integer> saveEventContents(
            int totalPriceBeforeDiscount, int date, Map<Menu, Integer> orders) {
        Map<EventType, Integer> eventContents = new HashMap<>();
        if (!EventService.isTheEventApplied(totalPriceBeforeDiscount)) {
            return null;
        }
        putEventContent(EventType.CHRISTMAS_D_DAY, calculateDiscountAmountOfChristmasEvent(date), eventContents);
        putEventContent(EventType.WEEKDAY, calculateDiscountAmountOfWeekdayEvent(date, orders), eventContents);
        putEventContent(EventType.WEEKEND, calculateDiscountAmountOfWeekendEvent(date, orders), eventContents);
        putEventContent(EventType.SPECIAL_DAY, checkAvailabilityForSpecialEvent(date), eventContents);
        putEventContent(EventType.GIVEAWAY, checkAvailabilityForGiveawayEvent(totalPriceBeforeDiscount), eventContents);
        
        return eventContents;
    }

    private static void putEventContent(EventType eventType, int discountAmount, Map<EventType, Integer> eventContents) {
        if (discountAmount != 0) {
            eventContents.put(eventType, discountAmount);
        }
    }

}
