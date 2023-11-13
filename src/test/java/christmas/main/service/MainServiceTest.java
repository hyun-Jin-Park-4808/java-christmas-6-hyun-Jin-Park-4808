package christmas.main.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.menus.type.Menu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainServiceTest {

    public static final int NUMBER_OF_TAPAS = 2;
    public static final int NUMBER_OF_CHOCOLATE_CAKE = 2;
    public static final int NUMBER_OF_T_BONE_STEAK = 1;
    public static final int MAX_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT = 3400;

    @DisplayName("할인 전 총 주문 금액을 계산한다.")
    @Test
    void calculateTotalPriceBeforeDiscount() {
        // given
        Map<Menu, Integer> input = new HashMap<>();

        input.put(Menu.TAPAS, NUMBER_OF_TAPAS);
        input.put(Menu.CHOCOLATE_CAKE, NUMBER_OF_CHOCOLATE_CAKE);
        input.put(Menu.T_BONE_STEAK, NUMBER_OF_T_BONE_STEAK);

        int expectedResult = Menu.TAPAS.getPrice() * NUMBER_OF_TAPAS
                + Menu.CHOCOLATE_CAKE.getPrice() * NUMBER_OF_CHOCOLATE_CAKE
                + Menu.T_BONE_STEAK.getPrice() * NUMBER_OF_T_BONE_STEAK;

        // when
        int result = MainService.calculateTotalPriceBeforeDiscount(input);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("25일 이후의 날짜를 입력하면 크리스마스 할인 금액은 0원이다.")
    @Test
    void calculateDiscountAmountOfChristmasEventByOutOfPeriod() {
        // given
        int input = 26;

        // when
        int result = MainService.calculateDiscountAmountOfChristmasEvent(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @DisplayName("25일을 입력하면 크리스마스 할인 금액은 3,400원이다.")
    @Test
    void calculateDiscountAmountOfChristmasEventByLastDateOfEvent() {
        // given
        int input = 25;

        // when
        int result = MainService.calculateDiscountAmountOfChristmasEvent(input);

        // then
        assertThat(result).isEqualTo(MAX_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT);
    }


}