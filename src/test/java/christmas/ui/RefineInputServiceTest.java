package christmas.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.main.vo.ReservationDate;
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

    @DisplayName("형식에 맞는 메뉴와 개수를 입력하면 정상적으로 메뉴를 저장한다.")
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
}