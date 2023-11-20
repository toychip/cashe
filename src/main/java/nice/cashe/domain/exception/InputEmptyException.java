package nice.cashe.domain.exception;

public class InputEmptyException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 인자가 비어있습니다.";
    public InputEmptyException() {
        super(MESSAGE);
    }
}
