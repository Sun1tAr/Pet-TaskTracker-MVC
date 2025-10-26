package my.pet.PetTaskTrackerMVC.service;

import lombok.extern.slf4j.Slf4j;
import my.pet.PetTaskTrackerMVC.entity.User;
import my.pet.PetTaskTrackerMVC.interfaces.UserDao;
import my.pet.PetTaskTrackerMVC.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return dao.getUserByUsername(userName);
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public Optional<User> createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return Optional.of(user);
    }
}
