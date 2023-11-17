package nice.cash.view;

public class OutputView {

    private final static java.util.logging.Logger logger = java.util.logging.Logger.getGlobal();

    public void print(String input) {
        log(input);
    }

    private void log(String input) {
        logger.info(input);
    }

}
