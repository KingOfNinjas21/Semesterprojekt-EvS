package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.services.UserService;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller for the user detail view.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Controller
@ViewScoped
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionInfoBean sessionInfo;

    /**
     * Attribute to cache the currently displayed user
     */
    private User user;

    /**
     * Sets the currently displayed user and reloads it form db. This user is
     * targeted by any further calls of
     * {@link #doReloadUser()}, {@link #doSaveUser()} and
     * {@link #doDeleteUser()}.
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        doReloadUser();
    }

    /**
     * Returns the currently displayed user.
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Action to force a reload of the currently displayed user.
     */
    public void doReloadUser() {
        user = userService.loadUser(user.getUsername());
    }

    /**
     * Action to save the currently displayed user.
     */
    public void doSaveUser() {
        user = this.userService.saveUser(user, sessionInfo.getCurrentUser());
    }

    /**
     * Action to delete the currently displayed user.
     */
    public void doDeleteUser() {
        this.userService.deleteUser(user, sessionInfo.getCurrentUser());
        user = null;
    }

}
