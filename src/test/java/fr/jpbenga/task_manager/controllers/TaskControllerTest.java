package fr.jpbenga.task_manager.controllers;

import fr.jpbenga.task_manager.models.Task;
import fr.jpbenga.task_manager.models.User;
import fr.jpbenga.task_manager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void shouldReturnTaskWhenExists() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Task task = new Task("task test", "This is a test task", LocalDate.now().plusDays(7), "Pending", user);
        task.setId(1L);

        given(taskService.findTaskById(1L)).willReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("task test"))
                .andExpect(jsonPath("$.description").value("This is a test task"))
                .andExpect(jsonPath("$.dueDate").value(task.getDueDate().toString()))
                .andExpect(jsonPath("$.status").value("Pending"));
    }
}
