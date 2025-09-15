package matty.command;

import matty.task.TaskList;
import matty.ui.Ui;
import matty.Storage;

/**
 * Represents a command that exits the application.
 *
 * When executed, it displays a goodbye message to the user and signals
 * the program to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * This displays a goodbye message via the Ui object.
     *
     * @param tasks   the TaskList (not used in this command)
     * @param ui      the Ui object to display the goodbye message
     * @param storage the Storage object (not used in this command)
     * @return a string containing the goodbye message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates that this command exits the application.
     *
     * @return true because executing this command should terminate the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}