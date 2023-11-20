package nice.cashe.domain.exception;

import static nice.cashe.domain.ApplicationConstants.INPUT_EMPTY_MESSAGE;

public class InputEmptyException extends IllegalArgumentException{

    public InputEmptyException() {
        super(INPUT_EMPTY_MESSAGE);
    }
}
