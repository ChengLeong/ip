package matty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 * Stores the description, start and end time of the event
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new Event task.
     *
     * @param description the description of the task
     * @param from the starting time of the event
     * @param to the ending time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task
     * including its description and formatted event date and time.
     *
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString()
                + " (from: " + from.format(outputFormat)
                + " to: " + to.format(outputFormat) + ")";
    }

    /**
     * Set the new start time of the event.
     * @param newFrom new start time of event
     */
    public void setFrom(LocalDateTime newFrom) {
        this.from = newFrom;
    }

    /**
     * Set the new end time of the event.
     * @param newTo new end time of the event.
     */
    public void setTo(LocalDateTime newTo) {
        this.to = newTo;
    }

    /**
     * Returns the string representation of this event task
     * formatted for saving to a file.
     *
     * @return the string representation formatted for file storage
     */
    @Override
    public String toFileString() {
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + (isDone ? "1" : "0") + " | "
                + description + " | "
                + from.format(saveFormat) + " | "
                + to.format(saveFormat);
    }
}
