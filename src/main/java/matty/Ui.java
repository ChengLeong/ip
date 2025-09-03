package matty;

import java.util.Scanner;
/**
 * Handles interactions with the user, including displaying messages and prompts.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm matty.Matty\nWhat can I do for you?");
    }

    /**
     * Displays the given error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the user's input string
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
