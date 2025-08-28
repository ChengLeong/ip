import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        // Ensure folder exists
        Files.createDirectories(filePath.getParent());

        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString()); // each Task subclass implements toFileString()
        }
        Files.write(filePath, lines);
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            Task task = parseLineToTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private Task parseLineToTask(String line) {
        // Format: TYPE | DONE | DESCRIPTION | (BY/FROM/TO)
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts.length > 3 ? parts[3] : "";
                String to = parts.length > 4 ? parts[4] : "";
                task = new Event(description, from, to);
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
