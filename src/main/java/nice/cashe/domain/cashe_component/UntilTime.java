package nice.cashe.domain.cashe_component;

import static nice.cashe.domain.ApplicationConstants.DATE_PATTERN;
import static nice.cashe.domain.ApplicationConstants.DATE_REGEX;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import nice.cashe.domain.exception.InputDateInvalidException;
import nice.cashe.domain.exception.InputEmptyException;

public class UntilTime {

    private final LocalDateTime timeValue;

    public UntilTime(String userTime) {
        validate(userTime);
        timeValue = createUserTime(userTime);
    }

    private void validate(String userTime) {
        validateBlank(userTime);
        validRule(userTime);
    }

    private void validateBlank(String userTime) {
        boolean invalidInput = (userTime == null) || (userTime.isEmpty());
        if (invalidInput) {
            throw new InputEmptyException();
        }
    }

    private void validRule(String userTime) {
        boolean isMatchRule = userTime.matches(DATE_REGEX);
        if (!isMatchRule) {
            throw new InputDateInvalidException();
        }
    }

    /**
     * 지정된 문자열 형식의 시간을 LocalDateTime 객체로 변환
     * 입력 형식은 "2000.04.27 21:30"
     * @param validatedDate 변환할 시간을 나타내는 문자열. 형식은 "yyyy.MM.dd HH:mm"이어야 함
     * @return 변환된 LocalDateTime 객체.
     */
    private LocalDateTime createUserTime(String validatedDate) {
        try {
            DateTimeFormatter formatter = getDateTimeFormatter();
            return LocalDateTime.parse(validatedDate, formatter);
        } catch (DateTimeParseException e) {
            throw new InputDateInvalidException();
        }
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    public LocalDateTime getTimeValue() {
        return timeValue;
    }
}
