package matty.task;

import java.util.ArrayList;

/**
 * Represents a list of {@link Task} objects in the Matty application.
 * Provides methods to add, retrieve, remove, and access all tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        int oldSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == oldSize + 1 : "Task list size should increase after adding a task";
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     *
     * @return the list of all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     *
     * @return boolean showing if the Task List is empty
     */
    public boolean isEmpty() {
        boolean b = tasks.size() == 0;
        return b;
    }
}
