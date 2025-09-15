package matty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a task with a deadline
 *  Stores the description and due date of the task
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline task.
     *
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task
     * including its description and formatted deadline date.
     *
     * @return the string representation of the deadline task
     */
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }

    /**
     * Set the deadline of the task to a new deadline
     * @param newBy the new deadline
     */
    public void setBy(LocalDate newBy){
        this.by = newBy;
    }
    /**
     * Returns the string representation of this deadline task
     * formatted for saving to a file.
     *
     * @return the string representation formatted for file storage
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}