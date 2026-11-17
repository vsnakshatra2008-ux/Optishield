
package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Task;

public class AdaptiveScheduler implements Scheduler {

    @Override
    public List<Task> schedule(List<Task> tasks) {
        List<Task> scheduledTasks = new ArrayList<>(tasks);

        Collections.sort(scheduledTasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                double score1 = (t1.getPriority() * 0.5)
                              + (t1.getDuration() * 0.3)
                              + (t1.getDeadline() * 0.2);

                double score2 = (t2.getPriority() * 0.5)
                              + (t2.getDuration() * 0.3)
                              + (t2.getDeadline() * 0.2);

                return Double.compare(score1, score2);
            }
        });

        return scheduledTasks;
    }
}
