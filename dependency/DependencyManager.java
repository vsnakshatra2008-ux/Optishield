
package dependency;

import java.util.ArrayList;
import java.util.List;
import model.Task;

public class DependencyManager {

    public List<Task> getReadyTasks(List<Task> tasks, List<Integer> completedTasks) {

        List<Task> readyTasks = new ArrayList<>();

        for (Task task : tasks) {

            boolean ready = true;

            for (int dependencyId : task.getDependencies()) {

                if (!completedTasks.contains(dependencyId)) {
                    ready = false;
                    break;
                }
            }

            if (ready && !completedTasks.contains(task.getTaskId())) {
                readyTasks.add(task);
            }
        }

        return readyTasks;
    }

    public boolean validateDependencies(List<Task> tasks) {

        for (Task task : tasks) {

            for (int dependencyId : task.getDependencies()) {

                if (dependencyId < 1 || dependencyId > tasks.size()) {
                    return false;
                }

                if (dependencyId == task.getTaskId()) {
                    return false;
                }
            }
        }

        return !hasCircularDependency(tasks);
    }

    private boolean hasCircularDependency(List<Task> tasks) {

        List<Integer> completed = new ArrayList<>();
        List<Integer> visiting = new ArrayList<>();

        for (Task task : tasks) {

            if (hasCycle(task, tasks, completed, visiting)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycle(Task task, List<Task> tasks,
                             List<Integer> completed, List<Integer> visiting) {

        if (completed.contains(task.getTaskId())) {
            return false;
        }

        if (visiting.contains(task.getTaskId())) {
            return true;
        }

        visiting.add(task.getTaskId());

        for (int dependencyId : task.getDependencies()) {

            for (Task dependencyTask : tasks) {

                if (dependencyTask.getTaskId() == dependencyId) {

                    if (hasCycle(dependencyTask, tasks, completed, visiting)) {
                        return true;
                    }
                }
            }
        }

        visiting.remove(Integer.valueOf(task.getTaskId()));
        completed.add(task.getTaskId());

        return false;
    }
}
