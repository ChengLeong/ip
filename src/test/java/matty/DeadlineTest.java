package matty;

import matty.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void toString_validDate_success() {
        Deadline d = new Deadline("return book", LocalDate.of(2023, 7, 17));
        assertEquals("[D][ ] return book (by: Jul 17 2023)", d.toString());
    }

    @Test
    public void toString_leapYearDate_success() {
        Deadline d = new Deadline("file taxes", LocalDate.of(2024, 2, 29));
        assertEquals("[D][ ] file taxes (by: Feb 29 2024)", d.toString());
    }

    @Test
    public void toFileString_notDone_success() {
        Deadline d = new Deadline("return book", LocalDate.of(2023, 7, 17));
        assertEquals("D | 0 | return book | 2023-07-17", d.toFileString());
    }

    @Test
    public void toFileString_done_success() {
        Deadline d = new Deadline("return book", LocalDate.of(2023, 7, 17));
        d.markAsDone();
        assertEquals("D | 1 | return book | 2023-07-17", d.toFileString());
    }
}
