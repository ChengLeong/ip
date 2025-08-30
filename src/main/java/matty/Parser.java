package matty;

public class Parser {
    public static String[] parse(String fullCommand) {
        return fullCommand.trim().split(" ", 2);
    }
}
