package nice.cashe.domain.exception;

import static nice.cashe.domain.ApplicationConstants.NOT_EXISTS_MESSAGE;

public class InputNotExistsKeyException extends IllegalArgumentException{

    public InputNotExistsKeyException() {
        super(NOT_EXISTS_MESSAGE);
    }
}
