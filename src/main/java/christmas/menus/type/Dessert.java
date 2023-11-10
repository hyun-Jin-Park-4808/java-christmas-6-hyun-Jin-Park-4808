package christmas.menus.type;

import java.util.ArrayList;
import java.util.List;

public enum Dessert {
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);
    private String name;
    private int price;

    private Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static List<String> desserts() {
        List<String> desserts = new ArrayList<>();
        desserts.add(CHOCOLATE_CAKE.name);
        desserts.add(ICE_CREAM.name);
        return desserts;
    }
}
