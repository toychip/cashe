package nice.cashe.domain.exception;

import static nice.cashe.domain.ApplicationConstants.CANT_INSTANCE_MESSAGE;

public class InvalidConstantException extends IllegalArgumentException{

    public InvalidConstantException() {
        super(CANT_INSTANCE_MESSAGE);
    }
}
