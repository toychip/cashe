package nice.cashe.domain.exception;

public class InputNotExistsKeyException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 존재하지 않는 키입니다.";
    public InputNotExistsKeyException() {
        super(MESSAGE);
    }
}
