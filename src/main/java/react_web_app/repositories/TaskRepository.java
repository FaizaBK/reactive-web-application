package react_web_app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import react_web_app.models.Task;

/**
 * Repository interface for accessing and performing CRUD operations on Task entities in MongoDB.
 * Extends ReactiveMongoRepository to provide reactive support for MongoDB interactions.
 */
@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
}