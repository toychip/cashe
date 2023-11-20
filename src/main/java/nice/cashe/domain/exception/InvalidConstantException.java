package nice.cashe.domain.exception;

public class InvalidConstantException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 해당 클래스는 상수 클래스입니다. 인스턴스화할 수 없습니다.";
    public InvalidConstantException() {
        super(MESSAGE);
    }
}
