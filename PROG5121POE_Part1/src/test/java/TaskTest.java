import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.prog5121poe_part1.Task;

public class TaskTest {

    @Test
    public void testTaskDescriptionSuccess() {
        Task task = new Task("Add Login Feature", 0, "Create login feature to authenticate users", "Robyn Harrison", 10, "To Do");
        assertTrue(task.checkTaskDescription());
    }

    @Test
    public void testTaskDescriptionFailure() {
        Task task = new Task("Add Login Feature", 0, "This task description is intentionally made way too long to test the fifty character limit failure restriction specified in the module guidelines.", "Robyn Harrison", 10, "To Do");
        assertFalse(task.checkTaskDescription());
    }

    @Test
    public void testCreateTaskID() {
        Task task = new Task("Add Login Feature", 0, "Login feature", "Robyn Harrison", 5, "To Do");
        assertEquals("AD:0:SON", task.createTaskID());
    }
}