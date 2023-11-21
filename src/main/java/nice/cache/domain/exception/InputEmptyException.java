package nice.cache.domain.exception;

import static nice.cache.domain.ApplicationConstants.INPUT_EMPTY_MESSAGE;

public class InputEmptyException extends IllegalArgumentException{

    public InputEmptyException() {
        super(INPUT_EMPTY_MESSAGE);
    }
}
