package fr.jpbenga.task_manager.services;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
import fr.jpbenga.task_manager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository ){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public Task updateTask(Long id, Task taskToUpdate){
        Task task = findTaskById(id);
        if (task != null){
            task.setTitle(taskToUpdate.getTitle());
            task.setDescription(taskToUpdate.getDescription());
            task.setDueDate(taskToUpdate.getDueDate());
            task.setStatus(taskToUpdate.getStatus());
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(Long id){
        Task task = findTaskById(id);
        if (task != null){
            taskRepository.delete(task);
        }
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findByUser(User user){
        return taskRepository.findByUser(user);
    }
}
