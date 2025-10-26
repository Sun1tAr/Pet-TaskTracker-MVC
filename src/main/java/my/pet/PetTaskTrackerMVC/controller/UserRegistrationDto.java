package my.pet.PetTaskTrackerMVC.controller;

import lombok.Data;

// DTO для регистрации
@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;


}
