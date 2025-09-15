package matty.command;

import matty.Storage;
import matty.task.Task;
import matty.task.TaskList;
import matty.ui.Ui;

/**
 * Represents a command to mark a task as done.
 *
 * When executed, this command marks the specified task in the TaskList as completed
 * and saves the updated list to storage.
 */
public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the mark command.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui object to display messages
     * @param storage the Storage object used to persist changes
     * @return a string showing the updated task or an error message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (args == null || args.isBlank()) {
            return ui.showError("You have to indicate which task to mark");
        }
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            Task t = tasks.get(index);
            t.markAsDone();
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