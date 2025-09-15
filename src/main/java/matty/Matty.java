package matty;


import matty.task.TaskList;
import matty.ui.Ui;
import java.io.IOException;
import matty.command.Command;
/**
 * The main class of the Matty program.
 * Initializes the components and runs the chatbot loop.
 */
public class Matty {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Creates a new Matty chatbot with the given file path.
     *
     * @param filePath the path of the file used to store tasks
     */
    public Matty(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String result = c.execute(tasks, ui, storage);
            storage.save(tasks.getAll());
            return result;
        } catch (Exception e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
}
