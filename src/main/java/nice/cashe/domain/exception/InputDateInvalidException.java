package nice.cashe.domain.exception;

import static nice.cashe.domain.ApplicationConstants.INVALID_FORMAT_MESSAGE;

public class InputDateInvalidException extends IllegalArgumentException{

    public InputDateInvalidException() {
        super(INVALID_FORMAT_MESSAGE);
    }
}
