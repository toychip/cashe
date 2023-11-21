package nice.cache.domain.exception;

import static nice.cache.domain.ApplicationConstants.INVALID_FORMAT_MESSAGE;

public class InputDateInvalidException extends IllegalArgumentException{

    public InputDateInvalidException() {
        super(INVALID_FORMAT_MESSAGE);
    }
}
