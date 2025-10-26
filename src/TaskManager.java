import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {

    private static final String FILE_NAME = "task.json";
    private final ArrayList<Task> tasksList = new ArrayList<>();
    LocalDate currentDate = LocalDate.now();

    // Add a new task
    public String addTask(String title, String description) {

        Task newTask = new Task();

        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setStatus("pending");
        newTask.setCreated_at(currentDate);
        newTask.setId(tasksList.isEmpty() ? 1 : tasksList.get(tasksList.size() - 1).getId() + 1);

        tasksList.add(newTask);
        return "Task '" + title + "' added successfully with ID " + newTask.getId();
    }

    // List tasks
    public void listTasks(String filter) {
        if (tasksList.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }

        for (Task task : tasksList) {
            if (filter.equals("all") || task.getStatus().equalsIgnoreCase(filter)) {

                System.out.println("ID: " + task.getId());

                System.out.println("Title: " + task.getTitle());

                System.out.println("Description: " + task.getDescription());

                System.out.println("Status: " + task.getStatus());

                System.out.println("Created at: " + task.getCreated_at());

                System.out.println("-------------------------");
            }
        }
    }

    //Update a task
    public void updateTask(int id, String newTitle, String newDescription, String newStatus) {

        boolean found = false;

        for (Task task : tasksList) {

            if (task.getId() == id) {

                if (!newTitle.isEmpty()) task.setTitle(newTitle);

                if (!newDescription.isEmpty()) task.setDescription(newDescription);

                if (!newStatus.isEmpty()) task.setStatus(newStatus);

                found = true;
                System.out.println("Task ID " + id + " updated successfully.");
                break;
            }
        }
        if (!found) System.out.println("No task with ID " + id);
    }

    // Delete a task
    public void deleteTask(int id) {
        boolean removed = tasksList.removeIf(task -> task.getId() == id);
        if (removed)
            System.out.println("Task with ID " + id + " deleted successfully.");
        else
            System.out.println("No task found with ID " + id);
    }

    // Mark a task
    public void markTask(int id, String status) {
        boolean found = false;
        for (Task task : tasksList) {
            if (task.getId() == id) {

                task.setStatus(status.toUpperCase());
                found = true;
                System.out.println("Task ID " + id + " marked as " + status.toUpperCase());
                break;
            }
        }
        if (!found) System.out.println("No task found with ID " + id);
    }


    public void saveTasksToFile() {

        try (Writer writer = new FileWriter(FILE_NAME)) {

            new Gson().toJson(tasksList, writer);
            System.out.println(" Tasks saved successfully to " + FILE_NAME);

        } catch (IOException e) {

            System.out.println(" Error saving tasks: " + e.getMessage());
        }
    }

    //Load tasks from JSON using Gson
    public void loadTasksFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {

            System.out.println("No existing file found. Starting with an empty task list.");
            return;
        }

        try (Reader reader = new FileReader(FILE_NAME)) {

            Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();

            ArrayList<Task> loadedTasks = new Gson().fromJson(reader, taskListType);

            tasksList.clear();

            if (loadedTasks != null) tasksList.addAll(loadedTasks);

            System.out.println(" Loaded " + tasksList.size() + " tasks from " + FILE_NAME);
        } catch (IOException e) {

            System.out.println(" Error loading tasks: " + e.getMessage());
        }
    }
}
