package my.pet.PetTaskTrackerMVC.interfaces;


import my.pet.PetTaskTrackerMVC.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String userName);

    boolean addUser(User user);

    Optional<User> createUser(String username, String password);


}
