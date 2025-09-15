package matty.task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        assert !description.trim().isEmpty() : "Task description should not be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether this task has been marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }
    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Return the string description of the task
     *
     * @return string containing the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Change the description of the task
     * @param newDescription the new description of the task
     */
    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return string containing the status and description of the task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns the string representation of this task for file storage.
     *
     * @return formatted string for file storage
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}