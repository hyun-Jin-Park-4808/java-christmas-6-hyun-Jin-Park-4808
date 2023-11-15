package christmas.menus.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000),
    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5000),
    ZERO_COKE(MenuType.DRINK, "제로콜라", 3000),
    RED_WINE(MenuType.DRINK, "레드와인", 60000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25000),
    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55000),
    BARBECUE_RIBS(MenuType.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25000);

    private MenuType menuType;
    private String name;
    private int price;

    private Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public static List<String> namesOfMenus() {
        return Arrays.stream(Menu.values()).map(Menu::getName).collect(Collectors.toList());
    }

    public static List<Menu> menus() {
        return Arrays.stream(Menu.values()).collect(Collectors.toList());
    }

    public static Menu compareSameName(String name) {
        for (Menu menu : menus()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public boolean isSameMenuType(MenuType menuType) {
        if (this.menuType.equals(menuType)) {
            return true;
        }
        return false;
    }
}
