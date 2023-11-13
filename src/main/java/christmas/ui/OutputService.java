package christmas.ui;

import christmas.main.service.EventService;
import christmas.menus.type.Menu;
import java.util.Map;

public class OutputService {
    public static void printTextForIntroduction() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printTextForReservationDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void printTextForOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void printTextForEventContents(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);
    }

    public static void printOrders(Map<Menu, Integer> orders) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            System.out.printf("%s %d개\n", order.getKey().getName(), order.getValue());
        }
    }

    public static void printTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.printf("%d원\n", totalPriceBeforeDiscount);
    }

    public static void printGiveAwayContent(boolean isApplicableToGiveawayEvent) {
        System.out.println("<증정 메뉴>");
        if (isApplicableToGiveawayEvent) {
            System.out.printf("%s 1개\n", Menu.CHAMPAGNE.getName());
        }
        if (!isApplicableToGiveawayEvent) {
            printTextForNullCase();
        }
    }

    public static void printTextForNullCase() {
        System.out.println("없음");
    }


}