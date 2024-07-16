package fr.jpbenga.task_manager.services;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
import fr.jpbenga.task_manager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            task.get().setDescription(taskToUpdate.getDescription());
            task.get().setTitle(taskToUpdate.getTitle());
            task.get().setStatus(taskToUpdate.getStatus());
            task.get().setDueDate(taskToUpdate.getDueDate());
            task.get().setUser(taskToUpdate.getUser());
        }
        return null;
    }

    public void deleteTask(Long id){
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(taskRepository::delete);
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findByUser(User user){
        return taskRepository.findByUser(user);
    }
}
