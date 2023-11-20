package nice.cashe.domain;

import nice.cashe.domain.exception.InvalidConstantException;

public final class ApplicationConstants {

    public static final String DATE_REGEX = "\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}";
    public static final String DATE_PATTERN = "yyyy.MM.dd HH:mm";
    public static final String INVALID_FORMAT_MESSAGE = "[ERROR] 인자 날짜가 포멧에 맞지 않습니다.";
    public static final String INPUT_EMPTY_MESSAGE = "[ERROR] 인자가 비어있습니다.";
    public static final String NOT_EXISTS_MESSAGE = "[ERROR] 존재하지 않는 키입니다.";
    public static final String CANT_INSTANCE_MESSAGE = "[ERROR] 해당 클래스는 상수 클래스입니다. 인스턴스화할 수 없습니다.";
    private ApplicationConstants() {
        throw new InvalidConstantException();
    }
}
