package fr.jpbenga.task_manager.controllers;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void shouldReturnAllTasks(){
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setDueDate(LocalDate.now());
        task1.setStatus("Pending");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setDueDate(LocalDate.now());
        task2.setStatus("Completed");

        List<Task> tasks = Arrays.asList(task1, task2);

        given(taskService.findAllTasks()).willReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()).containsExactly(task1, task2);

    }

    @Test
    void shouldReturnTaskById(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setDueDate(LocalDate.now());
        task.setStatus("Pending");

        given(taskService.findTaskById(1L)).willReturn(task);

        ResponseEntity<Task> response = taskController.getTaskById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(task);
    }

    @Test
    void shouldReturnNotFoundWhenTaskDoesNotExist(){
        given(taskService.findTaskById(1L)).willReturn(null);

        ResponseEntity<Task> response = taskController.getTaskById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
}
