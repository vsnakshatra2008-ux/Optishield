
package model;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private int taskId;
    private String taskName;
    private int duration;
    private int priority;
    private int deadline;
    private List<Integer> dependencies;

    public Task(int taskId, String taskName, int duration, int priority, int deadline) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.dependencies = new ArrayList<>();
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getDeadline() {
        return deadline;
    }

    public List<Integer> getDependencies() {
        return dependencies;
    }

    public void addDependency(int taskId) {
        if (!dependencies.contains(taskId)) {
            dependencies.add(taskId);
        }
    }
}
