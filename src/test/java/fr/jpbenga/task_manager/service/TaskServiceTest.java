package fr.jpbenga.task_manager.service;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.repositories.TaskRepository;
import fr.jpbenga.task_manager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest  {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTaskById(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setDueDate(LocalDate.now());
        task.setStatus("Pending");

        given(taskRepository.findById(1L)).willReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.findTaskById(1L);

        assertThat(foundTask).isNotNull();
        assertThat(foundTask.get().getId()).isEqualTo(1L);
    }

    @Test
    void shouldReturnNullWhenTaskDoesNotExist(){
        given(taskRepository.findById(1L)).willReturn(Optional.empty());

        Optional<Task> foundTask = taskService.findTaskById(1L);

        assertThat(foundTask).isEmpty();
    }
}
