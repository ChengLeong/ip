import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
