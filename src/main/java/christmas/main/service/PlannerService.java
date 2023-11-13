package christmas.main.service;

import static christmas.main.service.EventService.*;
import static christmas.main.service.ResultService.*;
import static christmas.ui.InputService.userInput;
import static christmas.ui.OutputService.*;
import static christmas.ui.RefineInputService.*;

import christmas.main.type.EventType;
import christmas.main.vo.ReservationDate;
import christmas.menus.type.Menu;
import java.util.Map;

public class PlannerService {

    public static void playEventPlanner() {
        printIntro();
        ReservationDate reservationDate = validateUserInputForReservationDate();
        printTextForOrder();
        Map<Menu, Integer> orders = validateUserInputForOrders();
        printTextForEventContentsAndOrders(reservationDate, orders);

        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount(orders);
        printTotalPriceBeforeDiscountAndGiveawayContent(totalPriceBeforeDiscount);

        Map<EventType, Integer> eventContents
                = saveEventContents(totalPriceBeforeDiscount, reservationDate, orders);
        printRemainResults(eventContents, totalPriceBeforeDiscount);
    }

    private static void printTextForEventContentsAndOrders(ReservationDate reservationDate, Map<Menu, Integer> orders) {
        printTextForEventContents(reservationDate);
        printOrders(orders);
    }

    private static void printRemainResults(Map<EventType, Integer> eventContents, int totalPriceBeforeDiscount) {
        printEventContents(eventContents);

        int totalDiscountedAmount = calculateTotalDiscountedAmount(eventContents);
        printTotalDiscountedAmount(totalDiscountedAmount);
        printAmountOfPayment(
                calculateAmountOfPayment(totalPriceBeforeDiscount, totalDiscountedAmount));
        printBadge(decideBadge(totalDiscountedAmount));
    }

    private static void printTotalPriceBeforeDiscountAndGiveawayContent(int totalPriceBeforeDiscount) {
        printTotalPriceBeforeDiscount(totalPriceBeforeDiscount);
        printGiveAwayContent(isApplicableToGiveawayEvent(totalPriceBeforeDiscount));
    }

    private static void printIntro() {
        printTextForIntroduction();
        printTextForReservationDate();
    }

    private static Map<Menu, Integer> validateUserInputForOrders() {
        Map<Menu, Integer> orders;
        while (true) {
            try {
                orders = saveOrdersByFoodTypeAndQuantity(splitUserInputForOrder(userInput()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orders;
    }

    private static ReservationDate validateUserInputForReservationDate() {
        ReservationDate date;
        while (true) {
            try {
                date = parseUserInputToReservationDate(userInput());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }
}
