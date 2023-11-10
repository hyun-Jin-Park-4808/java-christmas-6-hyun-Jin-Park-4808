package christmas.menus.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuServiceTest {

    @DisplayName("모든 메뉴를 한 문장으로 가져온다.")
    @Test
    void getAllMenus() {
        // given
        String expectedResult = "양송이수프 타파스 시저샐러드 "
                + "초코케이크 아이스크림 "
                + "제로콜라 레드와인 샴페인 "
                + "티본스테이크 바비큐립 해산물파스타 크리스마스파스타 ";

        // when
        String result = MenuService.getAllMenus();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}