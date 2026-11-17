
package scheduler;

import java.util.List;
import model.Task;

public interface Scheduler {
    List<Task> schedule(List<Task> tasks);
}
