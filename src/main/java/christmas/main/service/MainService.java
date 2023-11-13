package christmas.main.service;

import christmas.menus.type.Menu;
import java.util.Map;

public class MainService {

    public static int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> orders) {
        int totalPrice = 0;
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            int priceOfOrdersPerMenu = order.getKey().getPrice();
            int numberOfOrdersPerMenu = order.getValue();
            totalPrice += priceOfOrdersPerMenu * numberOfOrdersPerMenu;
        }
        return totalPrice;
    }
}
