import java.io.File;
import java.util.Scanner;

public class TaskTrackerCLI {
    public static void main(String[] args) {

        final String FILE_NAME = "task.json";

        TaskManager taskManager = new TaskManager();

        File file = new File(FILE_NAME);

        if (file.exists()) {

            System.out.println("File exists. Loading tasks...");
            taskManager.loadTasksFromFile();

        } else {

            System.out.println("File not found. Starting with an empty task list.");
        }


        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== TASK TRACKER ===");

            System.out.println("1. Add Task");

            System.out.println("2. List Tasks");

            System.out.println("3. Update Task");

            System.out.println("4. Delete Task");

            System.out.println("5. Mark Task");

            System.out.println("6. Save Tasks");

            System.out.println("7. Exit");

            System.out.print("Choose an option: ");


            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");

                    String title = scanner.nextLine();

                    System.out.print("Enter description: ");

                    String description = scanner.nextLine();

                    System.out.println(taskManager.addTask(title, description));
                }
                case 2 -> {
                    System.out.print("Enter filter (all/pending/completed): ");

                    String filter = scanner.nextLine();

                    taskManager.listTasks(filter);
                }
                case 3 -> {
                    System.out.print("Enter task ID to update: ");

                    int id = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("New title (leave empty to keep): ");

                    String newTitle = scanner.nextLine();
                    System.out.print("New description (leave empty to keep): ");

                    String newDescription = scanner.nextLine();
                    System.out.print("New status (leave empty to keep): ");

                    String newStatus = scanner.nextLine();
                    taskManager.updateTask(id, newTitle, newDescription, newStatus);
                }
                case 4 -> {

                    System.out.print("Enter task ID to delete: ");
                    int id = scanner.nextInt();

                    scanner.nextLine();
                    taskManager.deleteTask(id);
                }
                case 5 -> {
                    System.out.print("Enter task ID to mark: ");
                    int id = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Enter status (completed/pending): ");

                    String status = scanner.nextLine();
                    taskManager.markTask(id, status);
                }
                case 6 -> taskManager.saveTasksToFile();
                case 7 -> {
                    taskManager.saveTasksToFile();
                    running = false;
                    System.out.println("Exiting Task Tracker...");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
