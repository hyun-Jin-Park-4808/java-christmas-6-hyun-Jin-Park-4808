package christmas.main.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReservationDateTest {

    @DisplayName("1~31 범위 외의 숫자가 들어오면 예외가 발생한다.")
    @ValueSource(strings = {"-1", "0", "32"})
    @ParameterizedTest
    void createReservationDate(Integer input) {
        assertThatThrownBy(() -> new ReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}