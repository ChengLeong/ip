package matty.command;

import matty.Storage;
import matty.task.*;
import matty.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to update an existing task.
 *
 * Supports updating:
 * - Todo: description only
 * - Deadline: description and/or /by date
 * - Event: description, /from start time, and /to end time
 */
public class UpdateCommand extends Command {
    private final String args;

    /**
     * Constructs an UpdateCommand with the provided arguments.
     *
     * @param args the raw arguments, e.g., "2 /desc New description /by 2025-09-10"
     */
    public UpdateCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the update command.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui object to display messages
     * @param storage the Storage object used to persist changes
     * @return a confirmation message or an error message if the update fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = args.split(" ", 2);
            if (parts.length < 2) {
                return ui.showError("You must provide a task number and details to update.");
            }

            int index = Integer.parseInt(parts[0].trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                return ui.showError("Task number does not exist.");
            }

            Task t = tasks.get(index);
            String details = parts[1].trim();

            if (t instanceof Todo) {
                ((Todo) t).setDescription(details);

            }
            else if (t instanceof Deadline) {
                String detailsTrimmed = details.trim();

                if (!detailsTrimmed.toLowerCase().startsWith("/desc") && !detailsTrimmed.contains("/by")) {
                    return ui.showError("Invalid update command. Use '/desc <description> /by <date>' for deadlines.");
                }

                if (detailsTrimmed.toLowerCase().startsWith("/desc")) {
                    String[] dParts = detailsTrimmed.split(" /by ", 2);
                    String newDesc = dParts[0].substring(5).trim(); // remove "desc/"
                    ((Deadline) t).setDescription(newDesc);

                    if (dParts.length > 1) {
                        ((Deadline) t).setBy(LocalDate.parse(dParts[1].trim()));
                    }
                } else if (detailsTrimmed.contains("/by")) {
                    String[] dParts = detailsTrimmed.split(" /by ", 2);
                    ((Deadline) t).setDescription(dParts[0].trim());
                    ((Deadline) t).setBy(LocalDate.parse(dParts[1].trim()));
                }
            }

            else if (t instanceof Event) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                if (details.contains("/desc")) {
                    String newDesc = details.split("/desc")[1].split(" /from| /to")[0].trim();
                    ((Event) t).setDescription(newDesc);
                }

                if (details.contains("/from")) {
                    String fromStr = details.split("/from ")[1].split(" /to")[0].trim();
                    ((Event) t).setFrom(LocalDateTime.parse(fromStr, formatter));
                }

                if (details.contains("/to")) {
                    String toStr = details.split("/to ")[1].trim();
                    ((Event) t).setTo(LocalDateTime.parse(toStr, formatter));
                }
            }
            else {
                return ui.showError("Unsupported task type for update.");
            }

            storage.save(tasks.getAll());
            return t.toString(); // show nicely formatted task
        } catch (Exception e) {
            return ui.showError("Failed to update task: " + e.getMessage());
        }
    }
}
