
package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Task;
import performance.PerformanceAnalyzer;

public class CSVExporter {

    public void exportResults(List<Task> tasks, String algorithm,
                              PerformanceAnalyzer analyzer) {

        try {

            FileWriter writer = new FileWriter("OptiSched_Results.csv");

            writer.write("Algorithm," + algorithm + "\n");
            writer.write("Task ID,Task Name,Duration,Priority,Deadline\n");

            for (Task task : tasks) {

                writer.write(
                    task.getTaskId() + ","
                    + task.getTaskName() + ","
                    + task.getDuration() + ","
                    + task.getPriority() + ","
                    + task.getDeadline() + "\n"
                );
            }

            writer.write("\nPerformance Metrics\n");
            writer.write("Makespan," + analyzer.calculateMakespan(tasks) + "\n");
            writer.write("Average Waiting Time,"
                    + analyzer.calculateAverageWaitingTime(tasks) + "\n");
            writer.write("Late Tasks," + analyzer.countLateTasks(tasks) + "\n");

            writer.close();

            System.out.println("Results exported successfully!");
            System.out.println("File: OptiSched_Results.csv");

        }
        catch (IOException e) {
            System.out.println("Error while exporting results: " + e.getMessage());
        }
    }
}
