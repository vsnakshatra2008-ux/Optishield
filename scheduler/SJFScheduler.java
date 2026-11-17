
package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Task;

public class SJFScheduler implements Scheduler {

    @Override
    public List<Task> schedule(List<Task> tasks) {
        List<Task> scheduledTasks = new ArrayList<>(tasks);

        Collections.sort(scheduledTasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Integer.compare(t1.getDuration(), t2.getDuration());
            }
        });

        return scheduledTasks;
    }
}
