package christmas.menus.type;

import java.util.ArrayList;
import java.util.List;

public enum Appetizer {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private String name;
    private int price;

    private Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static List<String> appetizers() {
        List<String> appetizers = new ArrayList<>();
        appetizers.add(BUTTON_MUSHROOM_SOUP.name);
        appetizers.add(TAPAS.name);
        appetizers.add(CAESAR_SALAD.name);
        return appetizers;
    }
}
