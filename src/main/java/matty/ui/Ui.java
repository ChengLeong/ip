package matty.ui;

import matty.task.TaskList;
import matty.task.Task;

import java.util.Scanner;
/**
 * Handles interactions with the user, including displaying messages and prompts.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Shows the welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm matty.Matty\nWhat can I do for you?";
    }

    /**
     * Shows the goodbye message to the user.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a generic message.
     *
     * @param message the message to display
     */
    public String showMessage(String message) {
        assert message != null : "UI message should not be null";
        return message;
    }

    /**
     * Displays the given error message.
     *
     * @param message the error message to display
     */
    public String showError(String message) {
        assert message != null : "UI message should not be null";
        return " " + message;
    }

    /**
     * Displays confirmation when a task is added.
     *
     * @param task the task added
     * @param totalTasks the new total number of tasks
     */
    public String showAddedTask(Task task, int totalTasks) {
        return "Got it. I've added this task:\n"
        + "  " + task + "\n"
        + "Now you have " + totalTasks + " task(s) in the list.";
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks the list of tasks
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(String.format((i + 1) + "." + tasks.get(i) + "\n"));
        }
        return taskList.toString();
    }

    /**
     * Displays confirmation when a task is deleted.
     *
     * @param task the task deleted
     * @param totalTasks the new total number of tasks
     */
    public String showDeletedTask(Task task, int totalTasks) {
        return "Noted. I've removed this task: \n"
        + "  " + task + "\n" + "Now you have " + totalTasks + " task(s) in the list.";
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
