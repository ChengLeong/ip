package matty.command;

import matty.Storage;
import matty.task.Task;
import matty.task.TaskList;
import matty.ui.Ui;

/**
 * Represents a command to mark a task as not done.
 *
 * When executed, it updates the task's status to not done,
 * persists the change to storage, and returns a confirmation message.
 */
public class UnmarkCommand extends Command {
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the unmark command.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui object to display messages
     * @param storage the Storage object used to persist changes
     * @return a confirmation message or error message if unsuccessful
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (args == null || args.isBlank()) {
            return ui.showError("You have to indicate which task to unmark");
        }
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            Task t = tasks.get(index);
            t.markAsNotDone();
            storage.save(tasks.getAll());
            return t.toString();
        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number.");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Task number does not exist.");
        } catch (Exception e) {
            return ui.showError("Something went wrong: " + e.getMessage());
        }
    }
}
