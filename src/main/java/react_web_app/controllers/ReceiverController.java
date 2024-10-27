package react_web_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import react_web_app.models.Task;
import react_web_app.services.CounterService;
import reactor.core.publisher.Mono;

/**
 * REST Controller for managing counting tasks.
 * Provides endpoints to start a counting task and to create a new task.
 */
@RestController
@RequestMapping("api/receiver")
public class ReceiverController {
    /** Counting service used to execute and manage counting tasks. */
    @Autowired
    private CounterService counterService;

    /**
     * Endpoint to start a specific counting task.
     * The counting begins at zero and progresses to 1000 with a one-second delay between increments.
     *
     * @param taskId The ID of the task to be started.
     * @return A response with a message confirming the task has started.
     */
    @PostMapping("/start/{taskId}")
    public Mono<ResponseEntity<String>> startTask(@PathVariable String taskId) {
        // Starts the counting task by calling the service and returns a response indicating the task has been started
        return counterService.startCounting(taskId)
                .thenReturn(ResponseEntity.ok("Task " + taskId + " started and will count to 1000 with a 1-second delay."));
    }

    // Endpoint to create a new Task
    @PostMapping("/create")
    public Mono<ResponseEntity<Task>> createTask() {
        return counterService.createTask()
                .map(task -> ResponseEntity.ok(task));
    }
}
