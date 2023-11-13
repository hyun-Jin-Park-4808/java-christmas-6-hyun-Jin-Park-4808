package christmas.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.main.service.EventService;
import christmas.main.vo.ReservationDate;
import christmas.menus.type.Menu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RefineInputServiceTest {

    @DisplayName("정수가 아닌 값을 입력하면 예외를 발생시킨다.")
    @ValueSource(strings = {"-3.5", "숫자", "-"})
    @ParameterizedTest
    void parseUserInputToReservationDateByIncorrectFormat(String input) {
        assertThatThrownBy(() -> RefineInputService.parseUserInputToReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~31 사이의 정수를 입력하면 ReservationDate를 반환한다.")
    @ValueSource(strings = {"3", "5", "31"})
    @ParameterizedTest
    void parseUserInputToReservationDate(String input) {
        assertThat(RefineInputService.parseUserInputToReservationDate(input))
                .isEqualTo(new ReservationDate(Integer.parseInt(input)));
    }

    @DisplayName("형식에 맞지 않는 메뉴와 개수를 입력하면 예외를 발생시킨다.")
    @ValueSource(strings = {"시저 샐러드-1,초코 케이크-1", "시저샐러드1", "시저샐러드-1,,초코케이크-1", "시저샐러드-21"})
    @ParameterizedTest
    void splitUserInputForOrderByIncorrectFormat(String input) {
        assertThatThrownBy(() -> RefineInputService.splitUserInputForOrder(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 메뉴를 주문하면 예외를 발생시킨다.")
    @Test
    void splitUserInputForOrderByNonExistedMenu() {
        // given
        String input = "소고기파스타-1,제로사이다-2";

        // when & then
        assertThatThrownBy(() -> RefineInputService.splitUserInputForOrder(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("형식에 맞는 메뉴와 개수를 입력하면 정상적으로 메뉴를 String[] 타입으로 저장한다.")
    @Test
    void splitUserInputForOrder() {
        // given
        String input = "시저샐러드-1,초코케이크-1";
        String[] expectedResult = {"시저샐러드", "1", "초코케이크", "1"};

        // when
        String[] result = RefineInputService.splitUserInputForOrder(input);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("중복 메뉴를 입력하면 예외를 발생시킨다.")
    @Test
    void saveOrdersByDuplicatedFoodTypeAndQuantity() {
        // given
        String[] input = {"시저샐러드", "2", "시저샐러드", "3", "티본스테이크", "1"};
        Map<Menu, Integer> expectedResult = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> RefineInputService.saveOrdersByFoodTypeAndQuantity(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료 메뉴만 입력하면 예외를 발생시킨다.")
    @Test
    void saveOrdersConsistingOfDrinkMenuOnly() {
        // given
        String[] input = {"레드와인", "2", "제로콜라", "3"};
        Map<Menu, Integer> expectedResult = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> RefineInputService.saveOrdersByFoodTypeAndQuantity(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문 수량이 20개가 넘으면 예외를 발생시킨다.")
    @Test
    void saveOrdersMoreThanTwenty() {
        // given
        String[] input = {"시저샐러드", "15", "제로콜라", "7"};
        Map<Menu, Integer> expectedResult = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> RefineInputService.saveOrdersByFoodTypeAndQuantity(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("형식에 맞는 메뉴와 개수를 입력하면 정상적으로 메뉴를 Map<Menu, Integer> 형태로 저장한다.")
    @Test
    void saveOrdersByFoodTypeAndQuantity() {
        // given
        String[] input = {"시저샐러드", "2", "초코케이크", "3", "티본스테이크", "1"};
        Map<Menu, Integer> expectedResult = new HashMap<>();

        expectedResult.put(Menu.CAESAR_SALAD, 2);
        expectedResult.put(Menu.CHOCOLATE_CAKE, 3);
        expectedResult.put(Menu.T_BONE_STEAK, 1);

        // when
        Map<Menu, Integer> result = RefineInputService.saveOrdersByFoodTypeAndQuantity(input);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("총 주문 금액이 만 원보다 적으면 이벤트 적용 대상 금액이 아니다.")
    @Test
    void isTheEventApplied_False_Case() {
        // given
        int input = 9000;

        // when & then
        assertThat(EventService.isTheEventApplied(input)).isFalse();
    }

    @DisplayName("총 주문 금액이 만 원 이상이면 이벤트 적용 대상 금액이다.")
    @ParameterizedTest
    @ValueSource(strings = {"10000", "30000", "50000"})
    void isTheEventApplied_True_Case(Integer input) {

        // when & then
        assertThat(EventService.isTheEventApplied(input)).isTrue();
    }

}