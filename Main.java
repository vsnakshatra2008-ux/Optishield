
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Task;
import scheduler.*;
import dependency.DependencyManager;
import performance.PerformanceAnalyzer;
import performance.AlgorithmSelector;
import utils.CSVExporter;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("========== OPTISCHED ==========");

        // ENTER TASK DETAILS ONLY ONCE

        int n;

        while (true) {
            System.out.print("Enter number of tasks: ");
            n = sc.nextInt();

            if (n > 0) {
                break;
            }

            System.out.println("Number of tasks must be greater than 0.");
        }

        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for Task " + (i + 1));

            System.out.print("Task Name: ");
            String name = sc.nextLine();

            while (name.trim().isEmpty()) {
                System.out.print("Task name cannot be empty. Enter again: ");
                name = sc.nextLine();
            }

            int duration;

            while (true) {
                System.out.print("Duration: ");
                duration = sc.nextInt();

                if (duration > 0) {
                    break;
                }

                System.out.println("Duration must be greater than 0. Try again.");
            }

            int priority;

            while (true) {
                System.out.print("Priority: ");
                priority = sc.nextInt();

                if (priority > 0) {
                    break;
                }

                System.out.println("Priority must be greater than 0. Try again.");
            }

            int deadline;

            while (true) {
                System.out.print("Deadline: ");
                deadline = sc.nextInt();

                if (deadline > 0) {
                    break;
                }

                System.out.println("Deadline must be greater than 0. Try again.");
            }

            Task task = new Task(i + 1, name, duration, priority, deadline);

            int dependencyCount;

            while (true) {
                System.out.print("Number of dependencies: ");
                dependencyCount = sc.nextInt();

                if (dependencyCount >= 0 && dependencyCount <= i) {
                    break;
                }

                System.out.println("Enter a valid dependency count (0 to " + i + ").");
            }

            for (int j = 0; j < dependencyCount; j++) {

                System.out.print("Enter dependency Task ID: ");
                int dependencyId = sc.nextInt();

                if (dependencyId < 1 || dependencyId > n
                        || dependencyId == task.getTaskId()
                        || task.getDependencies().contains(dependencyId)) {

                    System.out.println("Invalid or duplicate dependency. Try again.");
                    j--;
                }
                else {
                    task.addDependency(dependencyId);
                }
            }

            sc.nextLine();
            tasks.add(task);
        }

        // INITIALIZE ANALYZER
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer();

        // REPEATED MENU
        while (true) {

            System.out.println("\n========== OPTISCHED MENU ==========");
            System.out.println("1. FCFS");
            System.out.println("2. SJF");
            System.out.println("3. Priority");
            System.out.println("4. Adaptive");
            System.out.println("5. Compare All Algorithms");
            System.out.println("6. Automatic Algorithm Selection");
            System.out.println("7. Dependency-Based Scheduling");
            System.out.println("8. Export Results to CSV");
            System.out.println("9. Exit");

            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayResult(
                        "FCFS",
                        new FCFSScheduler().schedule(tasks),
                        analyzer
                    );
                    break;

                case 2:
                    displayResult(
                        "SJF",
                        new SJFScheduler().schedule(tasks),
                        analyzer
                    );
                    break;

                case 3:
                    displayResult(
                        "Priority",
                        new PriorityScheduler().schedule(tasks),
                        analyzer
                    );
                    break;

                case 4:
                    displayResult(
                        "Adaptive",
                        new AdaptiveScheduler().schedule(tasks),
                        analyzer
                    );
                    break;

                case 5:
                    System.out.println("\n========== COMPARING ALL ALGORITHMS ==========");

                    displayResult(
                        "FCFS",
                        new FCFSScheduler().schedule(tasks),
                        analyzer
                    );

                    displayResult(
                        "SJF",
                        new SJFScheduler().schedule(tasks),
                        analyzer
                    );

                    displayResult(
                        "Priority",
                        new PriorityScheduler().schedule(tasks),
                        analyzer
                    );

                    displayResult(
                        "Adaptive",
                        new AdaptiveScheduler().schedule(tasks),
                        analyzer
                    );
                    break;

                case 6:
                    AlgorithmSelector selector = new AlgorithmSelector();
                    selector.compareAlgorithms(tasks);
                    break;

                case 7:

                    DependencyManager manager = new DependencyManager();

                    if (!manager.validateDependencies(tasks)) {

                        System.out.println("Invalid dependencies!");
                        System.out.println("Check task IDs and circular dependencies.");
                    }
                    else {

                        DependencyScheduler dependencyScheduler =
                            new DependencyScheduler();

                        List<Task> result = dependencyScheduler.schedule(
                            tasks, new AdaptiveScheduler()
                        );

                        if (!result.isEmpty()) {
                            displayResult(
                                "Dependency-Based Adaptive Scheduling",
                                result,
                                analyzer
                            );
                        }
                    }
                    break;

                case 8:

                    System.out.println("\n========== EXPORT RESULTS ==========");
                    System.out.println("1. FCFS");
                    System.out.println("2. SJF");
                    System.out.println("3. Priority");
                    System.out.println("4. Adaptive");
                    System.out.println("5. Dependency-Based Adaptive");

                    System.out.print("Choose algorithm to export: ");
                    int exportChoice = sc.nextInt();

                    List<Task> result = new ArrayList<>();
                    String algorithm = "";

                    if (exportChoice == 1) {
                        result = new FCFSScheduler().schedule(tasks);
                        algorithm = "FCFS";
                    }
                    else if (exportChoice == 2) {
                        result = new SJFScheduler().schedule(tasks);
                        algorithm = "SJF";
                    }
                    else if (exportChoice == 3) {
                        result = new PriorityScheduler().schedule(tasks);
                        algorithm = "Priority";
                    }
                    else if (exportChoice == 4) {
                        result = new AdaptiveScheduler().schedule(tasks);
                        algorithm = "Adaptive";
                    }
                    else if (exportChoice == 5) {

                        DependencyManager dependencyManager =
                            new DependencyManager();

                        if (!dependencyManager.validateDependencies(tasks)) {
                            System.out.println("Invalid dependencies!");
                        }
                        else {
                            DependencyScheduler dependencyScheduler =
                                new DependencyScheduler();

                            result = dependencyScheduler.schedule(
                                tasks, new AdaptiveScheduler()
                            );

                            algorithm = "Dependency-Based Adaptive";
                        }
                    }
                    else {
                        System.out.println("Invalid algorithm choice!");
                    }

                    if (!result.isEmpty()) {
                        CSVExporter exporter = new CSVExporter();
                        exporter.exportResults(result, algorithm, analyzer);
                    }
                    break;

                case 9:
                    System.out.println("\nThank you for using OPTISCHED!");
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1-9.");
            }
        }
    }

    public static void displayResult(
            String name,
            List<Task> tasks,
            PerformanceAnalyzer analyzer) {

        System.out.println("\n========== " + name + " ==========");

        System.out.print("Execution Order: ");

        for (Task task : tasks) {
            System.out.print(task.getTaskName() + " -> ");
        }

        System.out.println("\nMakespan: "
                + analyzer.calculateMakespan(tasks));

        System.out.println("Average Waiting Time: "
                + analyzer.calculateAverageWaitingTime(tasks));

        System.out.println("Late Tasks: "
                + analyzer.countLateTasks(tasks));
    }
}
