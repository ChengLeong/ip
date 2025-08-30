package matty;

import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm matty.Matty\nWhat can I do for you?");
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
