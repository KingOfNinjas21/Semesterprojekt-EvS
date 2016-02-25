package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.common.model.User;
import at.qe.sepm.skeleton.repositories.UserRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User loadUser(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
