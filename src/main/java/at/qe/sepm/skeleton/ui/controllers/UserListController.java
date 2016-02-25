package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.services.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller for the user list view.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Controller
public class UserListController {

    @Autowired
    private UserService userService;

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public Collection<User> getUsers() {
        return userService.getAllUsers();
    }

}
