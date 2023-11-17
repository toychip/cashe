package nice.cashe.view;

import java.util.Scanner;

public class InputView {
    public String read() {
        Scanner input = new Scanner(System.in);
        return input.next();
    }
}
