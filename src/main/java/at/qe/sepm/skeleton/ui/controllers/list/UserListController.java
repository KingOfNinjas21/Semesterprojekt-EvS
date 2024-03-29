package at.qe.sepm.skeleton.ui.controllers.list;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.services.UserService;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the user list view.
 *
 * This class is part of the skeleton project provided for students of the
 * course "Softwaredevelopment and Project Management" offered by the University
 * of Innsbruck.
 */
@Component
@Scope("view")
public class UserListController implements Serializable {

	private static final long serialVersionUID = -8157564173824649895L;
	
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
