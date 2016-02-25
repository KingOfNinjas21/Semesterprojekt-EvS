package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.repositories.UserRepository;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Service for accessing and manipulating user data.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Component
@Scope("application")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns a collection of all users.
     *
     * @return
     */
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Loads a single user identified by its username.
     *
     * @param username the username to search for
     * @return the user with the given username
     */
    public User loadUser(String username) {
        return userRepository.findFirstByUsername(username);
    }

    /**
     * Saves the user. This method will also set {@link User#createDate} for new
     * entities or {@link User#updateDate} for updated entities. The user
     * requesting this operation will also be stored as {@link User#createDate}
     * or {@link User#updateUser} respectively.
     *
     * @param user the user to save
     * @param currentUser the user requesting this operation
     * @return the updated user
     */
    public User saveUser(User user, User currentUser) {
        if (user.isNew()) {
            user.setCreateDate(new Date());
            user.setCreateUser(currentUser);
        } else {
            user.setUpdateDate(new Date());
            user.setUpdateUser(currentUser);
        }
        return userRepository.save(user);
    }

    /**
     * Deletes the user.
     *
     * @param user the user to delete
     * @param currentUser the user requesting this operation
     */
    public void deleteUser(User user, User currentUser) {
        userRepository.delete(user);
        // :TODO: write some audit log stating who and when this user was permanently deleted.
    }

}
