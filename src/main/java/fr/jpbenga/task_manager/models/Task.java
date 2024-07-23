package fr.jpbenga.task_manager.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String title, String description, LocalDate dueDate, String status, User user) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.user = user;
    }
}
