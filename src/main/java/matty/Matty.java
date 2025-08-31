package matty;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * Runs the main execution loop of the chatbot.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();
                String[] parts = input.split(" ", 2);
                String cmd = parts[0];

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if (cmd.equals("mark")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! You have to indicate which task to mark");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task t = tasks.get(index);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);

                } else if (cmd.equals("unmark")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! You have to indicate which task to unmark");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task t = tasks.get(index);
                    t.markAsNotDone();
                    System.out.println("Ok, I've marked this task as not done yet.");
                    System.out.println(t);

                } else if (cmd.equals("todo")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! The description of a todo cannot be empty.");
                    Task t = new Todo(parts[1]);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("deadline")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! The description of a deadline cannot be empty.");
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    String description = deadlineParts[0].trim();
                    String by = deadlineParts.length > 1 ? deadlineParts[1].trim() : "";
                    try {
                        Task t = new Deadline(description, LocalDate.parse(by));
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd format");
                    }

                } else if (cmd.equals("event")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! The description of an event cannot be empty.");
                    String[] eventParts = parts[1].split(" /from | /to ", 3);
                    String description = eventParts[0].trim();
                    String fromStr = eventParts.length > 1 ? eventParts[1].trim() : "";
                    String toStr = eventParts.length > 2 ? eventParts[2].trim() : "";
                    if (fromStr.isEmpty() || toStr.isEmpty()) {
                        System.out.println("Please provide both /from and /to times in format yyyy-MM-dd HH:mm");
                        continue;
                    }
                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    try {
                        LocalDateTime from = LocalDateTime.parse(fromStr, inputFormat);
                        LocalDateTime to = LocalDateTime.parse(toStr, inputFormat);
                        Task t = new Event(description, from, to);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Please use the format: yyyy-MM-dd HH:mm (e.g., 2025-09-01 14:00).");
                    }

                } else if (cmd.equals("delete")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! You have to indicate which task to delete");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task t = tasks.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("on")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! Please provide a date in yyyy-MM-dd format.");
                    LocalDate queryDate = LocalDate.parse(parts[1]);
                    System.out.println("Here are the tasks on " + queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
                    int count = 1;
                    for (Task t : tasks.getAll()) {
                        if (t instanceof Deadline) {
                            LocalDate deadlineDateTime = ((Deadline) t).by;
                            if (deadlineDateTime.equals(queryDate)) {
                                System.out.println(count + "." + t);
                                count++;
                            }
                        } else if (t instanceof Event) {
                            LocalDateTime eventDateTime = ((Event) t).from;
                            if (eventDateTime.toLocalDate().equals(queryDate)) {
                                System.out.println(count + "." + t);
                                count++;
                            }
                        }
                    }
                    if (count == 1) {
                        System.out.println("No tasks found on this date.");
                    }

                } else {
                    throw new MattyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                // save tasks after each command
                storage.save(tasks.getAll());

            } catch (MattyException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Runs the main execution loop of the chatbot.
     */
    public static void main(String[] args) {
        new Matty("data/tasks.txt").run();
    }
}
