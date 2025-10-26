import java.time.LocalDate;

public class Task {

    private int id;
    private String title;
    private String description;
    private String status;
    private LocalDate created_at;

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDate getCreated_at() { return created_at; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setCreated_at(LocalDate created_at) { this.created_at = created_at; }


    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + "Title: " + title + "\n"
                + "Description: " + description + "\n"
                + "Status: " + status + "\n"
                + "Created at: " + created_at + "\n"
                + "-----------------------------";
    }
}
