package matty.command;

import matty.task.TaskList;
import matty.ui.Ui;
import matty.Storage;

/**
 * Represents a command that is not recognized by the parser.
 *
 * When executed, this command simply returns an error message indicating
 * that the user's input was invalid.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the unknown command.
     *
     * @param tasks   the TaskList containing all tasks (not used here)
     * @param ui      the Ui object to display messages
     * @param storage the Storage object used to persist changes (not used here)
     * @return an error message indicating the command is not recognized
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError("I don't understand that command.");
    }
}
