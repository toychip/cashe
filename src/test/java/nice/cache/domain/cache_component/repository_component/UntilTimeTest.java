package nice.cache.domain.cache_component.repository_component;


import static nice.cache.domain.ApplicationConstants.INVALID_FORMAT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import nice.cache.domain.exception.InputDateInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UntilTimeTest {

    @ParameterizedTest
    @DisplayName("숫자 포멧에 맞게 생성되는지 검증")
    @CsvSource("2000.04.27 21:30")
    void input(String userInputTime){
        LocalDateTime toTime = LocalDateTime.of(2000, 04, 27, 21, 30);
        //given

        UntilTime untilTime = new UntilTime(userInputTime);
        //when

        LocalDateTime timeValue = untilTime.getTimeValue();
        Assertions.assertEquals(toTime, timeValue);
        //then
    }

    @ParameterizedTest
    @DisplayName("숫자 포멧에 맞지 않으면 InputDateInvalidException 발생")
    @CsvSource("2000.04.27 21-30")
    void input2(String userInputTime){
        // given

        // when && then
        InputDateInvalidException e = assertThrows(InputDateInvalidException.class,
                () -> new UntilTime(userInputTime));
        String errorMessage = e.getMessage();

        assertEquals(INVALID_FORMAT_MESSAGE, errorMessage);

    }

}