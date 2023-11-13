package christmas.main.service;

import christmas.menus.type.Menu;
import java.util.Map;

public class MainService {

    public static final int MIN_PRICE_FOR_EVENT = 10000;

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
}
