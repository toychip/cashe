package nice.cashe.domain.exception;

public class InputDateInvalidException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 인자 날짜가 포멧에 맞지 않습니다.";
    public InputDateInvalidException() {
        super(MESSAGE);
    }
}
