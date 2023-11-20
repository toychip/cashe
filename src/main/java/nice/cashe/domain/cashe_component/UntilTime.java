package nice.cashe.domain.cashe_component;

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
        boolean isMatchRule = userTime.matches("\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}");
        if (!isMatchRule) {
            throw new InputDateInvalidException();
        }
    }

    /**
     * 2000.04.27 21:30 형식으로 입력 들어옴
     * @param userTime
     * @return LocalDateTime
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
        return DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    }

    public LocalDateTime getTimeValue() {
        return timeValue;
    }
}
