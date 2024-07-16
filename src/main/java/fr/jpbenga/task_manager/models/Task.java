package fr.jpbenga.task_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
