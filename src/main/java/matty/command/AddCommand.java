package matty.command;

import matty.task.*;
import matty.ui.Ui;
import matty.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    // Type of task to add
    private final String type;
    // Arguments string containing task details
    private final String args;

    /**
     * Constructs an AddCommand with the specified task type and arguments.
     *
     * @param type the type of task ("todo", "deadline", "event")
     * @param args the arguments string containing task details
     */
    public AddCommand(String type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Executes the AddCommand: parses the input arguments, creates the corresponding Task,
     * adds it to the TaskList, saves the updated list, and returns a confirmation message.
     *
     * @param tasks   the TaskList to add the task to
     * @param ui      the Ui object to display messages
     * @param storage the Storage object to save tasks to file
     * @return a message confirming the task has been added, or an error message
     * @throws IOException if saving to file fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task;

        switch (type) {
        case "todo":
            task = new Todo(args.trim());
            break;

        case "deadline":
            // args format: "submit report /by 2025-09-20"
            String[] deadlineParts = args.split("/by", 2);
            String desc = deadlineParts[0].trim();
            LocalDate by = LocalDate.parse(deadlineParts[1].trim());
            task = new Deadline(desc, by);
            break;

        case "event":
            // args format: "team meeting /from 2025-09-20 10:00 /to 2025-09-20 12:00"
            String[] eventParts = args.split(" /from | /to ");
            if (eventParts.length < 3) {
                return ui.showError("Please provide description, /from and /to times in yyyy-MM-dd HH:mm format.");
            }
            String eDesc = eventParts[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(eventParts[1].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(eventParts[2].trim(), formatter);
            task = new Event(eDesc, from, to);
            break;

        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        // Add the created task to the task list
        tasks.add(task);

        // Save the updated task list to the file
        storage.save(tasks.getAll());

        return ui.showAddedTask(task, tasks.size());
    }
}
