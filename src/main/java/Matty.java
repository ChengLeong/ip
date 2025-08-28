import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("./data/matty.txt");

        ArrayList<Task> task;
        try {
            task = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            task = new ArrayList<>();
        }

        System.out.println("Hello! I'm Matty\nWhat can I do for you?");

        while (true) {
            try {
                String input = sc.nextLine();
                String[] parts = input.split(" ", 2);
                String cmd = parts[0];

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < task.size(); i++) {
                        System.out.println((i + 1) + "." + task.get(i));
                    }
                } else if (cmd.equals("mark")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! You have to indicate the item on the list is to be marked");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task t = task.get(index);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                } else if (cmd.equals("unmark")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! You have to indicate the item on the list is to be unmarked");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task t = task.get(index);
                    t.markAsNotDone();
                    System.out.println("Ok, I've marked this task as not done yet.");
                    System.out.println(t);
                } else if (cmd.equals("todo")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! The description of a todo cannot be empty.");
                    Task t = new Todo(input.substring(5));
                    task.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                } else if (cmd.equals("deadline")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! The description of a deadline cannot be empty.");
                    String[] deadlineParts = input.substring(9).split(" /by ", 2);
                    String description = deadlineParts[0].trim();
                    String by = deadlineParts.length > 1 ? deadlineParts[1].trim() : "";
                    Task t = new Deadline(description, by);
                    task.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                } else if (cmd.equals("event")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! The description of a event cannot be empty.");
                    String[] eventParts = input.substring(6).split(" /from | /to ", 3);
                    Task t = new Event(
                            eventParts[0].trim(),
                            eventParts.length > 1 ? eventParts[1].trim() : "",
                            eventParts.length > 2 ? eventParts[2].trim() : ""
                    );
                    task.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                } else if (cmd.equals("delete")) {
                    if (parts.length == 1) throw new MattyException("OOPS!!! You have to indicate the item on the list is to be deleted");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task t = task.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                } else {
                    throw new MattyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                // SAVE AFTER EVERY CHANGE
                try {
                    storage.save(task);
                } catch (IOException e) {
                    System.out.println("Error saving tasks: " + e.getMessage());
                }

            } catch (MattyException e) {
                System.out.println(" " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
