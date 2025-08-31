package matty;

/**
 * Represents a simple to-do task.
 */
public class Todo extends Task {
    /**
     * Creates a new To-do task.
     *
     * @param description the description of the to-do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return formatted string with to-do information
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}