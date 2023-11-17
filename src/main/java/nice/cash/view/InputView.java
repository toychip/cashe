package nice.cash.view;

import java.util.Scanner;

public class InputView {
    public int read() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
