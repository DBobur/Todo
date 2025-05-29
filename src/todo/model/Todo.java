package todo.model;

import java.util.UUID;

public class Todo {
    private UUID id;
    private UUID userId;
    private String title;
    private String description;
    private TodoStatus status;

    {
        this.id = UUID.randomUUID();
        this.status = TodoStatus.CREATED;
    }

    public Todo(UUID userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String todoInfo(){
        return title + " " + description;
    }
}
