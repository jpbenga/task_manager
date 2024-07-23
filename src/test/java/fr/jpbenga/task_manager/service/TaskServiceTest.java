package fr.jpbenga.task_manager.service;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
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
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnEmptyWhenTaskDoesNotExist(){
        given(taskRepository.findById(1L)).willReturn(Optional.empty());

        Optional<Task> task = taskService.findTaskById(1L);

        assertThat(task).isEmpty();
    }

    @Test
    void shouldReturnTaskWhenTaskExists(){
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Task task = new Task("task test", "This is a test task", LocalDate.now().plusDays(7), "Pending", user);
        task.setId(1L);

        given(taskRepository.findById(1L)).willReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.findTaskById(1L);

        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getId()).isEqualTo(1L);
        assertThat(foundTask.get().getTitle()).isEqualTo("task test");
        assertThat(foundTask.get().getDescription()).isEqualTo("This is a test task");
        assertThat(foundTask.get().getDueDate()).isEqualTo(LocalDate.now().plusDays(7));
        assertThat(foundTask.get().getStatus()).isEqualTo("Pending");
        assertThat(foundTask.get().getUser()).isEqualTo(user);
    }
}
