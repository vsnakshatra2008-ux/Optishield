
package scheduler;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import dependency.DependencyManager;

public class DependencyScheduler {

    public List<Task> schedule(List<Task> tasks, Scheduler scheduler) {

        List<Task> scheduledTasks = new ArrayList<>();
        List<Integer> completedTasks = new ArrayList<>();

        DependencyManager manager = new DependencyManager();

        while (scheduledTasks.size() < tasks.size()) {

            List<Task> readyTasks = manager.getReadyTasks(tasks, completedTasks);

            if (readyTasks.isEmpty()) {
                System.out.println("Error: Circular or invalid dependency detected!");
                return new ArrayList<>();
            }

            List<Task> selectedTasks = scheduler.schedule(readyTasks);

            Task nextTask = selectedTasks.get(0);

            scheduledTasks.add(nextTask);
            completedTasks.add(nextTask.getTaskId());
        }

        return scheduledTasks;
    }
}
