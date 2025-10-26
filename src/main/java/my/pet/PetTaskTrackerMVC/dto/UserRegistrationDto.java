package my.pet.PetTaskTrackerMVC.dto;

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
