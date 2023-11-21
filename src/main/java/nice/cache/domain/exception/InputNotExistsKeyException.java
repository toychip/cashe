package nice.cache.domain.exception;

import static nice.cache.domain.ApplicationConstants.NOT_EXISTS_MESSAGE;

public class InputNotExistsKeyException extends IllegalArgumentException{

    public InputNotExistsKeyException() {
        super(NOT_EXISTS_MESSAGE);
    }
}
