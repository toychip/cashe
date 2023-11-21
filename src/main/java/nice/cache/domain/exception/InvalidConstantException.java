package nice.cache.domain.exception;

import static nice.cache.domain.ApplicationConstants.CANT_INSTANCE_MESSAGE;

public class InvalidConstantException extends IllegalArgumentException{

    public InvalidConstantException() {
        super(CANT_INSTANCE_MESSAGE);
    }
}
