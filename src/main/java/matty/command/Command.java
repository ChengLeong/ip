package matty.command;

import matty.task.TaskList;
import matty.ui.Ui;
import matty.Storage;

import java.io.IOException;

/**
 * Represents a general command in the application.
 *
 * All specific commands (e.g., AddCommand, DeleteCommand, UpdateCommand) extend this class
 * and implement the execute method to define their behavior.
 */
public abstract class Command {

    /**
     * Executes the command using the given TaskList, Ui, and Storage.
     *
     * Each subclass must implement this method to define its specific behavior.
     *
     * @param tasks   the TaskList on which the command operates
     * @param ui      the Ui object to interact with the user
     * @param storage the Storage object to persist tasks
     * @return a string message to display to the user
     * @throws IOException if there is an error saving to storage
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    public boolean isExit() {
        return false;
    }
}
