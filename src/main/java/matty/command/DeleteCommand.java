package matty.command;

import matty.task.TaskList;
import matty.task.Task;
import matty.ui.Ui;
import matty.Storage;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String args;

    /**
     * Creates a new DeleteCommand with the given arguments.
     *
     * @param args the argument specifying which task to delete
     */
    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the delete command: removes a task from the TaskList,
     * saves the updated list to storage, and returns a message to the user.
     *
     * @param tasks   the TaskList to delete a task from
     * @param ui      the Ui object to display messages
     * @param storage the Storage object to persist the updated list
     * @return a message to display to the user
     * @throws IOException if saving to storage fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(args) - 1;
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            storage.save(tasks.getAll());
            return ui.showDeletedTask(removedTask, tasks.size());
        } catch (NumberFormatException e) {
            return ui.showError("Invalid delete command.");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Task number does not exist.");
        }
    }
}
