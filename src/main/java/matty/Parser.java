package matty;

import matty.command.*;

/**
 * Parses user input into commands that can be executed.
 */
public class Parser {

    /**
     * Converts a full input string into a corresponding Command object.
     *
     * @param fullCommand the raw user input
     * @return a Command object that can be executed
     */
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandWord, arguments);

        case "list":
            return new ListCommand();

        case "delete":
            return new DeleteCommand(arguments);

        case "mark":
            return new MarkCommand(arguments);

        case "unmark":
            return new UnmarkCommand(arguments);

        case "update":
            return new UpdateCommand(arguments);

        case "bye":
            return new ExitCommand();

        default:
            return new UnknownCommand();
        }
    }
}
