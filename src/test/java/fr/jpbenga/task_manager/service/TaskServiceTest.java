package fr.jpbenga.task_manager.service;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.repositories.TaskRepository;
import fr.jpbenga.task_manager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        Task task = new Task();
        task.setId(1L);
        task.setTitle("task test");

        given(taskRepository.findById(1L)).willReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.findTaskById(1L);

        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getId()).isEqualTo(1L);
    }
}
