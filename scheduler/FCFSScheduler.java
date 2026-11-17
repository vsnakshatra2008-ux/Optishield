
package scheduler;

import java.util.ArrayList;
import java.util.List;
import model.Task;

public class FCFSScheduler implements Scheduler {

    @Override
    public List<Task> schedule(List<Task> tasks) {
        List<Task> scheduledTasks = new ArrayList<>(tasks);

        return scheduledTasks;
    }
}
