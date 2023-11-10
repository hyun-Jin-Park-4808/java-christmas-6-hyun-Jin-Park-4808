package christmas.menus.type;

import java.util.ArrayList;
import java.util.List;

public enum Main {
    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private String name;
    private int price;

    private Main(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static List<String> mains() {
        List<String> mains = new ArrayList<>();
        mains.add(T_BONE_STEAK.name);
        mains.add(BARBECUE_RIBS.name);
        mains.add(SEAFOOD_PASTA.name);
        mains.add(CHRISTMAS_PASTA.name);
        return mains;
    }
}
