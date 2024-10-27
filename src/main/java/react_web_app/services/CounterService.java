package react_web_app.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import react_web_app.models.Task;
import react_web_app.repositories.TaskRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.time.Instant;

/**
 * Service for managing counting tasks, including starting and creating new tasks.
 * Uses reactive programming to handle asynchronous and continuous counting operations.
 */
@Service
public class CounterService {
    /** Repository for interacting with Task entities in the MongoDB database. */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Starts a counting task for a given task ID. If the task exists, it is updated and started from its last state.
     * Otherwise, a new task is created and started. The task counts from 0 to 1000 with a 1-second delay between each count.
     *
     * @param taskId The ID of the task to start.
     * @return A Mono emitting the Task object once the task is started and completed.
     */
    public Mono<Task> startCounting(String taskId) {
        // Attempt to find the existing task by its ID
        return taskRepository.findById(taskId)
                .flatMap(existingTask -> {
                    // If the task exists, update its status to "IN_PROGRESS"
                    existingTask.setStatus("IN_PROGRESS");
                    existingTask.setRequestedAt(Instant.now());

                    // Save the updated task and proceed with counting
                    return taskRepository.save(existingTask)
                            .thenMany(
                                    // Emit numbers from 0 to 1000 with a 1-second delay
                                    Flux.range(0, 1001)
                                            .delayElements(Duration.ofSeconds(1))
                                            .flatMap(count -> {
                                                // Update task with current count
                                                existingTask.setCurrentValue(count);
                                                return taskRepository.save(existingTask);
                                            })
                            )
                            .then(
                                    // After counting to 1000, mark task as completed
                                    taskRepository.findById(taskId)
                                            .flatMap(t -> {
                                                t.setStatus("COMPLETED");
                                                t.setCompletedAt(Instant.now());
                                                return taskRepository.save(t);
                                            })
                            );
                })
                .switchIfEmpty(
                        // If the task does not exist, create a new one
                        Mono.defer(() -> {
                            Task newTask = new Task(taskId, "IN_PROGRESS", Instant.now(), null);
                            return taskRepository.save(newTask)
                                    .thenMany(
                                            // Emit numbers from 0 to 1000 with a 1-second delay
                                            Flux.range(0, 1001)
                                                    .delayElements(Duration.ofSeconds(1))
                                                    .flatMap(count -> {
                                                        // Update task with current count
                                                        newTask.setCurrentValue(count);
                                                        return taskRepository.save(newTask);
                                                    })
                                    )
                                    .then(
                                            // After counting to 1000, mark task as completed
                                            taskRepository.findById(taskId)
                                                    .flatMap(t -> {
                                                        t.setStatus("COMPLETED");
                                                        t.setCompletedAt(Instant.now());
                                                        return taskRepository.save(t);
                                                    })
                                    );
                        })
                );
    }

    /**
     * Creates a new counting task with an initial status of "CREATED" and a current count of zero.
     *
     * @return A Mono emitting the newly created Task object.
     */
    public Mono<Task> createTask() {
        Task task = new Task();
        task.setStatus("CREATED");
        task.setRequestedAt(Instant.now());
        task.setCurrentValue(0);
        return taskRepository.save(task);
    }
}
