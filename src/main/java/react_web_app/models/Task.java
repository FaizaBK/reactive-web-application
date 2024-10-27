package react_web_app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

/**
 * Represents a Task entity in the MongoDB "tasks" collection.
 * A task contains details about its current status, progress, and timestamps for its creation and completion.
 */
@Document(collection = "tasks")
public class Task {

    /** Unique identifier for the task. */
    @Id
    private String id;
    private String status;
    private int currentValue;
    private Instant requestedAt;
    private Instant completedAt;

    /** Default constructor for the Task entity. */
    public Task() {}

    /**Parameterized constructor for creating a task with specific attributes.*/
    public Task(String taskId, String inProgress, Instant requestedAt, Instant completedAt) {
        this.id = taskId;
        this.status = inProgress;
        this.requestedAt = requestedAt;
        this.completedAt = completedAt;
    }

    // Getters and setters


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getCurrentValue() {
        return currentValue;
    }


    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }


    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }
}
