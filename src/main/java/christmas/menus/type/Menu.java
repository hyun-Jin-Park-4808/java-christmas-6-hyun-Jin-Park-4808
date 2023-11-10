package christmas.menus.type;

import java.util.ArrayList;
import java.util.List;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000),
    CHOCOLATE_CAKE(MenuType.DESSERT,"초코케이크", 15000),
    ICE_CREAM(MenuType.DESSERT,"아이스크림", 5000),
    ZERO_COKE(MenuType.DRINK, "제로콜라", 3000),
    RED_WINE(MenuType.DRINK,"레드와인", 60000),
    CHAMPAGNE(MenuType.DRINK,"샴페인", 25000),
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

    public static List<String> namesOfMenus() {
        List<String> namesOfMenus = new ArrayList<>();
        namesOfMenus.add(BUTTON_MUSHROOM_SOUP.name);
        namesOfMenus.add(TAPAS.name);
        namesOfMenus.add(CAESAR_SALAD.name);
        namesOfMenus.add(CHOCOLATE_CAKE.name);
        namesOfMenus.add(ICE_CREAM.name);
        namesOfMenus.add(ZERO_COKE.name);
        namesOfMenus.add(RED_WINE.name);
        namesOfMenus.add(CHAMPAGNE.name);
        namesOfMenus.add(T_BONE_STEAK.name);
        namesOfMenus.add(BARBECUE_RIBS.name);
        namesOfMenus.add(SEAFOOD_PASTA.name);
        namesOfMenus.add(CHRISTMAS_PASTA.name);
        return namesOfMenus;
    }

    public static List<Menu> menus() {
        List<Menu> menus = new ArrayList<>();
        menus.add(BUTTON_MUSHROOM_SOUP);
        menus.add(TAPAS);
        menus.add(CAESAR_SALAD);
        menus.add(CHOCOLATE_CAKE);
        menus.add(ICE_CREAM);
        menus.add(ZERO_COKE);
        menus.add(RED_WINE);
        menus.add(CHAMPAGNE);
        menus.add(T_BONE_STEAK);
        menus.add(BARBECUE_RIBS);
        menus.add(SEAFOOD_PASTA);
        menus.add(CHRISTMAS_PASTA);
        return menus;
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
