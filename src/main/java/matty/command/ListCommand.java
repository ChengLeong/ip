package matty.command;

import matty.task.TaskList;
import matty.ui.Ui;
import matty.Storage;

/**
 * Represents a command that lists all tasks in the task list.
 *
 * When executed, it retrieves all tasks from the TaskList and displays them
 * using the Ui object.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui object used to display the task list
     * @param storage the Storage object (not used in this command)
     * @return a string representing the formatted list of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}