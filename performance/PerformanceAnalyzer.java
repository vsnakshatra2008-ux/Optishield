
package performance;

import java.util.List;
import model.Task;
import scheduler.Scheduler;

public class PerformanceAnalyzer {

    public double calculateMakespan(List<Task> tasks) {
        int totalDuration = 0;

        for (Task task : tasks) {
            totalDuration += task.getDuration();
        }

        return totalDuration;
    }

    public double calculateAverageWaitingTime(List<Task> tasks) {
        int waitingTime = 0;
        int totalWaitingTime = 0;

        for (Task task : tasks) {
            totalWaitingTime += waitingTime;
            waitingTime += task.getDuration();
        }

        if (tasks.size() == 0) {
            return 0;
        }

        return (double) totalWaitingTime / tasks.size();
    }

    public int countLateTasks(List<Task> tasks) {
        int currentTime = 0;
        int lateTasks = 0;

        for (Task task : tasks) {
            currentTime += task.getDuration();

            if (currentTime > task.getDeadline()) {
                lateTasks++;
            }
        }

        return lateTasks;
    }

    public long calculateExecutionTime(Scheduler scheduler, List<Task> tasks) {

        long startTime = System.nanoTime();

        scheduler.schedule(tasks);

        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
