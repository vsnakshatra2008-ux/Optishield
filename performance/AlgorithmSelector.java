
package performance;

import java.util.List;
import model.Task;
import scheduler.*;

public class AlgorithmSelector {

    private PerformanceAnalyzer analyzer = new PerformanceAnalyzer();

    public void compareAlgorithms(List<Task> tasks) {

        Scheduler[] schedulers = {
            new FCFSScheduler(),
            new SJFScheduler(),
            new PriorityScheduler(),
            new AdaptiveScheduler()
        };

        String[] names = {
            "FCFS",
            "SJF",
            "Priority",
            "Adaptive"
        };

        double bestScore = Double.MAX_VALUE;
        String bestAlgorithm = "";

        System.out.println("\n========== ALGORITHM COMPARISON ==========");

        for (int i = 0; i < schedulers.length; i++) {

            List<Task> result = schedulers[i].schedule(tasks);

            double makespan = analyzer.calculateMakespan(result);
            double waitingTime = analyzer.calculateAverageWaitingTime(result);
            int lateTasks = analyzer.countLateTasks(result);

            long executionTime = analyzer.calculateExecutionTime(
                schedulers[i], tasks
            );

            double score = (0.5 * makespan)
                         + (0.3 * waitingTime)
                         + (10 * lateTasks);

            System.out.println("\n" + names[i]);
            System.out.println("Makespan: " + makespan);
            System.out.println("Average Waiting Time: " + waitingTime);
            System.out.println("Late Tasks: " + lateTasks);
            System.out.println("Execution Time: " + executionTime + " ns");
            System.out.println("Performance Score: " + score);

            if (score < bestScore) {
                bestScore = score;
                bestAlgorithm = names[i];
            }
        }

        System.out.println("\n========== RECOMMENDATION ==========");
        System.out.println("Best Algorithm: " + bestAlgorithm);
        System.out.println("Best Performance Score: " + bestScore);
    }
}
