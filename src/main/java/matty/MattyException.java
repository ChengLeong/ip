package matty;

/**
 * Represents a custom exception used in the Matty application
 * to signal specific error conditions.
 */
public class MattyException extends Exception {

    /**
     * Constructs a {@code MattyException} with the specified detail message.
     *
     * @param message the detail message that describes the cause of the exception
     */
    public MattyException(String message) {
        super(message);
    }
}
