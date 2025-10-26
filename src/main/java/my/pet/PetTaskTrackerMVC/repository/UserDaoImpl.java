package my.pet.PetTaskTrackerMVC.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import my.pet.PetTaskTrackerMVC.entity.User;
import my.pet.PetTaskTrackerMVC.interfaces.UserDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> getUserByUsername(String username) {
        User user = em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if (user == null){
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public boolean addUser(User user) {
        boolean result = true;
        try {
            em.persist(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = false;
        }
        return result;
    }

}
