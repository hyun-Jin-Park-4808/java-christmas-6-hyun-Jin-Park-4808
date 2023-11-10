package christmas.menus.service;

import christmas.menus.type.Menu;
import java.util.List;

public class MenuService {
    public static String getAllMenus() {
        StringBuilder allMenus = new StringBuilder();
        appendMenus(Menu.namesOfMenus(), allMenus);
        return String.valueOf(allMenus);
    }

    public static void appendMenus(List<String> menus, StringBuilder allMenus) {
        for (String menu : menus) {
            allMenus.append(menu);
            allMenus.append(" ");
        }
    }
}
