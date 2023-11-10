package christmas.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.main.vo.ReservationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputServiceTest {

    @DisplayName("정수가 아닌 값을 입력하면 예외를 발생시킨다.")
    @ValueSource(strings = {"-3.5", "숫자", "-"})
    @ParameterizedTest
    void parseUserInputToReservationDateByIncorrectFormat(String input) {
        assertThatThrownBy(() -> InputService.parseUserInputToReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~31 사이의 정수를 입력하면 ReservationDate를 반환한다.")
    @ValueSource(strings = {"3", "5", "31"})
    @ParameterizedTest
    void parseUserInputToReservationDate(String input) {
        assertThat(InputService.parseUserInputToReservationDate(input))
                .isEqualTo(new ReservationDate(Integer.parseInt(input)));
    }
}