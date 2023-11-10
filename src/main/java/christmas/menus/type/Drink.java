package christmas.menus.type;

import java.util.ArrayList;
import java.util.List;

public enum Drink {
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private String name;
    private int price;

    private Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static List<String> drinks() {
        List<String> drinks = new ArrayList<>();
        drinks.add(ZERO_COKE.name);
        drinks.add(RED_WINE.name);
        drinks.add(CHAMPAGNE.name);
        return drinks;
    }
}
