package christmas.main.service;

import static christmas.constants.Constant.MIN_AMOUNT_FOR_SANTA_BADGE;
import static christmas.constants.Constant.MIN_AMOUNT_FOR_STAR_BADGE;
import static christmas.constants.Constant.MIN_AMOUNT_FOR_TREE_BADGE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.main.type.BadgeType;
import christmas.main.type.EventType;
import christmas.main.vo.ReservationDate;
import christmas.menus.type.Menu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultServiceTest {

    @DisplayName("평일 이벤트가 적용되는 날짜를 입력하면 평일 이벤트를 포함하여 이벤트 내역이 저장된다.")
    @Test
    void saveEventContentsByWeekday() {
        // given
        Map<Menu, Integer> orders = EventServiceTest.testOrders();
        int totalPriceBeforeDiscount = EventService.calculateTotalPriceBeforeDiscount(orders);
        ReservationDate date = new ReservationDate(7); // 목요일(평일)
        Map<EventType, Integer> expectedResult = new HashMap<>();
        expectedResult.put(EventType.CHRISTMAS_D_DAY,
                EventService.calculateDiscountAmountOfChristmasEvent(date));
        expectedResult.put(EventType.WEEKDAY,
                EventService.calculateDiscountAmountOfWeekdayEvent(date, orders));
        expectedResult.put(EventType.GIVEAWAY,
                EventService.checkAvailabilityForGiveawayEvent(totalPriceBeforeDiscount));

        // when
        Map<EventType, Integer> eventContents
                = ResultService.saveEventContents(totalPriceBeforeDiscount, date, orders);

        // then
        assertThat(eventContents).isEqualTo(expectedResult);
    }

    @DisplayName("주말 이벤트가 적용되는 날짜를 입력하면 주말 이벤트를 포함하여 이벤트 내역이 저장된다.")
    @Test
    void saveEventContentsByWeekend() {
        // given
        Map<Menu, Integer> orders = EventServiceTest.testOrders();
        int totalPriceBeforeDiscount = EventService.calculateTotalPriceBeforeDiscount(orders);
        ReservationDate date = new ReservationDate(8); // 금요일(주말)
        Map<EventType, Integer> expectedResult = new HashMap<>();
        expectedResult.put(EventType.CHRISTMAS_D_DAY,
                EventService.calculateDiscountAmountOfChristmasEvent(date));
        expectedResult.put(EventType.WEEKEND,
                EventService.calculateDiscountAmountOfWeekendEvent(date, orders));
        expectedResult.put(EventType.GIVEAWAY,
                EventService.checkAvailabilityForGiveawayEvent(totalPriceBeforeDiscount));

        // when
        Map<EventType, Integer> eventContents
                = ResultService.saveEventContents(totalPriceBeforeDiscount, date, orders);

        // then
        assertThat(eventContents).isEqualTo(expectedResult);
    }

    @DisplayName("특별 이벤트가 적용되는 날짜를 입력하면 특별 이벤트를 포함하여 이벤트 내역이 저장된다.")
    @Test
    void saveEventContentsBySpecialDay() {
        // given
        Map<Menu, Integer> orders = EventServiceTest.testOrders();
        int totalPriceBeforeDiscount = EventService.calculateTotalPriceBeforeDiscount(orders);
        ReservationDate date = new ReservationDate(3); // 일요일(평일), 특별 이벤트 적용 날짜
        Map<EventType, Integer> expectedResult = new HashMap<>();
        expectedResult.put(EventType.CHRISTMAS_D_DAY,
                EventService.calculateDiscountAmountOfChristmasEvent(date));
        expectedResult.put(EventType.WEEKDAY,
                EventService.calculateDiscountAmountOfWeekdayEvent(date, orders));
        expectedResult.put(EventType.GIVEAWAY,
                EventService.checkAvailabilityForGiveawayEvent(totalPriceBeforeDiscount));
        expectedResult.put(EventType.SPECIAL_DAY,
                EventService.checkAvailabilityForSpecialEvent(date));

        // when
        Map<EventType, Integer> eventContents
                = ResultService.saveEventContents(totalPriceBeforeDiscount, date, orders);

        // then
        assertThat(eventContents).isEqualTo(expectedResult);
    }

    @DisplayName("할인 전 총주문 금액이 10000원 이하이면 null을 반환한다.")
    @Test
    void saveEventContentsByLowerPrice() {
        // given
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.ICE_CREAM, 1);
        int totalPriceBeforeDiscount = EventService.calculateTotalPriceBeforeDiscount(orders);
        ReservationDate date = new ReservationDate(3);

        // when
        Map<EventType, Integer> eventContents
                = ResultService.saveEventContents(totalPriceBeforeDiscount, date, orders);

        // then
        assertThat(eventContents).isEqualTo(null);
    }


    @DisplayName("평일 할인과 특별 이벤트와 증정 이벤트가 적용된 총 할인 금액을 계산한다.")
    @Test
    void calculateTotalDiscountedAmount() {
        // given
        Map<Menu, Integer> orders = EventServiceTest.testOrders();
        int totalPriceBeforeDiscount = EventService.calculateTotalPriceBeforeDiscount(orders);
        ReservationDate date = new ReservationDate(3); // 일요일(평일), 특별 이벤트 적용 날짜
        Map<EventType, Integer> eventContents
                = ResultService.saveEventContents(totalPriceBeforeDiscount, date, orders);

        int expectedResult = EventService.calculateDiscountAmountOfChristmasEvent(date)
                + EventService.calculateDiscountAmountOfWeekdayEvent(date, orders)
                + EventService.checkAvailabilityForGiveawayEvent(totalPriceBeforeDiscount)
                + EventService.checkAvailabilityForSpecialEvent(date);

        // when
        int totalDiscountedAmount = ResultService.calculateTotalDiscountedAmount(eventContents);
        // then
        assertThat(totalDiscountedAmount).isEqualTo(expectedResult);
    }

    @DisplayName("혜택 내역이 없으면 0원을 반환한다.")
    @Test
    void calculateTotalDiscountedAmountByNull() {
        assertThat(ResultService.calculateTotalDiscountedAmount(null)).isEqualTo(0);
    }

    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    @Test
    void calculateAmountOfPayment() {
        // given
        int totalPriceBeforeDiscount = 150000;
        int totalDiscountAmount = 5000;
        int expectedResult = totalPriceBeforeDiscount - totalDiscountAmount;

        // when & then
        assertThat(ResultService
                .calculateAmountOfPayment(totalPriceBeforeDiscount, totalDiscountAmount))
                .isEqualTo(expectedResult);
    }

    @DisplayName("총혜택 금액이 2만원 이상이면 산타 배지를 받는다.")
    @Test
    void decideBadgeByMinAmountForSantaBadge() {
        // given
        int totalDiscountAmount = MIN_AMOUNT_FOR_SANTA_BADGE;

        // when & then
        assertThat(ResultService.decideBadge(totalDiscountAmount)).isEqualTo(BadgeType.SANTA);
    }

    @DisplayName("총혜택 금액이 1만원 이상 2만원 미만이면 트리 배지를 받는다.")
    @Test
    void decideBadgeByMinAmountForTreeBadge() {
        // given
        int totalDiscountAmount = MIN_AMOUNT_FOR_TREE_BADGE;

        // when & then
        assertThat(ResultService.decideBadge(totalDiscountAmount)).isEqualTo(BadgeType.TREE);
    }

    @DisplayName("총혜택 금액이 5천원 이상 1만원 미만이면 별 배지를 받는다.")
    @Test
    void decideBadgeByMinAmountForStarBadge() {
        // given
        int totalDiscountAmount = MIN_AMOUNT_FOR_STAR_BADGE;

        // when & then
        assertThat(ResultService.decideBadge(totalDiscountAmount)).isEqualTo(BadgeType.STAR);
    }

    @DisplayName("총혜택 금액이 5천원 미만이면 아무 배지도 못 받는다.")
    @Test
    void decideBadgeByLowerAmount() {
        // given
        int totalDiscountAmount = 4000;

        // when & then
        assertThat(ResultService.decideBadge(totalDiscountAmount)).isEqualTo(null);
    }
}