package nice.cashe.domain;

import nice.cashe.domain.exception.InvalidConstantException;

public final class ApplicationConstants {

    public static final String DATE_REGEX = "\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}";
    public static final String DATE_PATTERN = "yyyy.MM.dd HH:mm";
    private ApplicationConstants() {
        throw new InvalidConstantException();
    }
}
