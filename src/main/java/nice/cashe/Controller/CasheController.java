package nice.cashe.Controller;

import nice.cashe.domain.Cashe;
import nice.cashe.view.InputView;
import nice.cashe.view.OutputView;

public class CasheController {

    private final InputView inputView;
    private final OutputView outputView;

    public CasheController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cashe cashe = new Cashe();
        outputView.printPut();
        String key = inputView.read();
        String key = inputView.read();

        cashe.put();
    }
}
