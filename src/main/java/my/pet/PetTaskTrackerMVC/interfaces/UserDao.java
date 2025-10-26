package my.pet.PetTaskTrackerMVC.interfaces;

import my.pet.PetTaskTrackerMVC.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUserByUsername(String username);

    boolean addUser(User user);
}
