package fr.jpbenga.task_manager.services;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
import fr.jpbenga.task_manager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task taskToUpdate) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(taskToUpdate.getDescription());
            task.setTitle(taskToUpdate.getTitle());
            task.setStatus(taskToUpdate.getStatus());
            task.setDueDate(taskToUpdate.getDueDate());
            task.setUser(taskToUpdate.getUser());
            return taskRepository.save(task);
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
