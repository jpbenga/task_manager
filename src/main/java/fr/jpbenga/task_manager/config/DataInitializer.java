package fr.jpbenga.task_manager.config;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
import fr.jpbenga.task_manager.repositories.TaskRepository;
import fr.jpbenga.task_manager.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword("password1");
            userRepository.save(user1);

            User user2 = new User();
            user2.setUsername("user2");
            user2.setPassword("password2");
            userRepository.save(user2);

            taskRepository.save(new Task("Task 1", "Description for task 1", LocalDate.now(), "Pending", user1));
            taskRepository.save(new Task("Task 2", "Description for task 2", LocalDate.now(), "Pending", user1));
            taskRepository.save(new Task("Task 3", "Description for task 3", LocalDate.now(), "Pending", user2));
            taskRepository.save(new Task("Task 4", "Description for task 4", LocalDate.now(), "Pending", user2));
            taskRepository.save(new Task("Task 5", "Description for task 5", LocalDate.now(), "Pending", user2));
        };
    }
}
