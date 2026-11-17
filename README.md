# OptiSched – Adaptive Task Scheduling and Performance Optimization System

## Overview

OptiSched is a Java-based task scheduling system designed to organize tasks and identify efficient execution sequences using different scheduling algorithms.

The system accepts task details such as duration, priority, deadline, and dependencies. It compares multiple scheduling algorithms using performance metrics and recommends the algorithm with the best overall performance score.

## Features

- **FCFS Scheduling:** Executes tasks in the order they are received.
- **SJF Scheduling:** Prioritizes tasks with shorter execution durations.
- **Priority Scheduling:** Executes tasks based on their priority levels.
- **Adaptive Scheduling:** Uses a heuristic based on priority, duration, and deadline.
- **Algorithm Comparison:** Compares all four scheduling algorithms.
- **Automatic Algorithm Selection:** Recommends an algorithm based on its performance score.
- **Dependency Management:** Ensures prerequisite tasks are completed before dependent tasks.
- **Performance Analysis:** Calculates makespan, average waiting time, late tasks, and execution time.
- **CSV Export:** Exports scheduling results and performance metrics into a CSV file.
- **Input Validation:** Checks task details and dependency inputs.

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections Framework
- Java I/O
- Java Exception Handling
- Multithreading concepts (where applicable)

## Project Structure

```text
OptiSched/
│
├── src/
│   ├── Main.java
│   │
│   ├── model/
│   │   ├── Task.java
│   │   └── Machine.java
│   │
│   ├── scheduler/
│   │   ├── Scheduler.java
│   │   ├── FCFSScheduler.java
│   │   ├── SJFScheduler.java
│   │   ├── PriorityScheduler.java
│   │   ├── AdaptiveScheduler.java
│   │   └── DependencyScheduler.java
│   │
│   ├── dependency/
│   │   └── DependencyManager.java
│   │
│   ├── performance/
│   │   ├── PerformanceAnalyzer.java
│   │   └── AlgorithmSelector.java
│   │
│   └── utils/
│       └── CSVExporter.java
│
└── README.md
```

## Input Parameters

Each task is defined using the following parameters:

| Parameter | Description |
|---|---|
| Task ID | Unique identifier for each task |
| Task Name | Name of the task |
| Duration | Time required to complete the task |
| Priority | Importance level of the task |
| Deadline | Expected completion time |
| Dependencies | Tasks that must be completed beforehand |

## Performance Metrics

OptiSched evaluates algorithms using:

- **Makespan:** Total time required to complete all tasks in the current single-machine model.
- **Average Waiting Time:** Average time tasks spend waiting before execution.
- **Late Tasks:** Number of tasks completed after their deadlines.
- **Execution Time:** Time taken by the scheduling algorithm to execute.

The system uses a weighted performance score:

`Performance Score = (0.5 × Makespan) + (0.3 × Average Waiting Time) + (10 × Late Tasks)`

A lower score indicates better performance according to the project's scoring criteria.

## How to Run

### Prerequisites

- Java Development Kit (JDK)
- Visual Studio Code or any Java-compatible IDE
- Git (optional)

### Compile

Open the terminal inside the `src` directory and run:

```powershell
javac Main.java (Get-ChildItem model,scheduler,dependency,performance,utils -Filter *.java -Recurse).FullName
```

### Execute

```powershell
java Main
```

Follow the console menu to enter task details and select a scheduling option.

## Real-World Applications

The scheduling concepts demonstrated in OptiSched can be applied to:

- Industrial production and workflow planning.
- Healthcare and laboratory task coordination.
- Cloud computing task management.
- Project and deadline management.
- Educational laboratory scheduling.

## Future Enhancements

- Multi-machine task scheduling.
- Resource allocation and optimization.
- Graphical user interface.
- Advanced adaptive scheduling techniques.
- More detailed performance visualization.

## Project Status

**Completed – Java Console-Based Prototype**

OptiSched demonstrates task scheduling, dependency handling, algorithm comparison, and performance-based algorithm selection using Java.

---

**Note:** The current implementation is a prototype using a simplified single-machine scheduling model. Real-world deployment would require additional resource and operational constraints.
