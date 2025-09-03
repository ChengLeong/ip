package matty;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Task class.
 */
public class TaskTest {

    @Test
    public void markAsDone_success() {
        Task t = new Task("read book");
        t.markAsDone();
        assertTrue(t.isDone());
    }

    @Test
    public void toString_notDone_success() {
        Task t = new Task("read book");
        assertEquals("[ ] read book", t.toString());
    }

    @Test
    public void toString_done_success() {
        Task t = new Task("read book");
        t.markAsDone();
        assertEquals("[X] read book", t.toString());
    }
}
