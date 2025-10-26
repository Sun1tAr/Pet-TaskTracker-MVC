package my.pet.PetTaskTrackerMVC.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor @AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Название не может быть пустым")
    private String title;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String description;

    @Column(name = "is_completed")
    private Boolean isDone = false;

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @FutureOrPresent(message = "Дата выполнения не может быть в прошлом")
    private LocalDate dueDate;

    // Getters and Setters
    public Long getId() {
            return id;
        }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }




}
