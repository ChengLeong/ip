package matty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;
    private final DateTimeFormatter deadlineFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter eventFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
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
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String byStr = parts.length > 3 ? parts[3] : "";
                try {
                    LocalDate by = LocalDate.parse(byStr, deadlineFormat);
                    task = new Deadline(description, by);
                } catch (DateTimeParseException e) {
                    System.out.println("Warning: Could not load deadline \"" + description +
                            "\" due to invalid date format: " + byStr);
                }
                break;
            case "E":
                String fromStr = parts.length > 3 ? parts[3] : "";
                String toStr = parts.length > 4 ? parts[4] : "";
                try {
                    LocalDateTime from = LocalDateTime.parse(fromStr, eventFormat);
                    LocalDateTime to = LocalDateTime.parse(toStr, eventFormat);
                    task = new Event(description, from, to);
                } catch (DateTimeParseException e) {
                    System.out.println("Warning: Could not load event \"" + description +
                            "\" due to invalid date/time format: " + fromStr + " to " + toStr);
                }
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
