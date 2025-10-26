package my.pet.PetTaskTrackerMVC.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Task> tasks;

}
