package fr.jpbenga.task_manager.repositories;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
