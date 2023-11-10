package christmas.menus.service;

import christmas.menus.type.Appetizer;
import christmas.menus.type.Dessert;
import christmas.menus.type.Drink;
import christmas.menus.type.Main;
import java.util.List;

public class MenuService {
    public static String getAllMenus() {
        StringBuilder allMenus = new StringBuilder();
        appendMenus(Appetizer.appetizers(), allMenus);
        appendMenus(Dessert.desserts(), allMenus);
        appendMenus(Drink.drinks(), allMenus);
        appendMenus(Main.mains(), allMenus);

        return String.valueOf(allMenus);
    }

    public static void appendMenus(List<String> menus, StringBuilder allMenus) {
        for (String menu : menus) {
            allMenus.append(menu);
            allMenus.append(" ");
        }
    }

}
