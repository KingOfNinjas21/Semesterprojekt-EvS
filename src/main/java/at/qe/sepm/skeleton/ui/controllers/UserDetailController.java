package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.services.UserService;
import at.qe.sepm.skeleton.utils.PasswordGenerator;
import at.qe.sepm.skeleton.utils.UserRolesView;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Controller for the user detail view.
 *
 * This class is part of the skeleton project provided for students of the
 * course "Softwaredevelopment and Project Management" offered by the University
 * of Innsbruck.
 */
@Component
@Scope("view")
public class UserDetailController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRolesView userRoleView;

    /**
     * Attribute to cache the currently displayed user
     */
    private User user;
    private User newUser;
    
    
    @PostConstruct
    private void init() {
    	newUser = new User();
    }

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

    public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
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
        user = this.userService.saveUser(user);
    }

    /**
     * Action to delete the currently displayed user.
     */
    public void doDeleteUser() {
        this.userService.deleteUser(user);
        user = null;
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    public void doAddUser() {
    	
    	if ((newUser.getUsername() == null) ||
    			(newUser.getUsername().length() <= 3)) {
    		//TODO: return message
    		return;
    	}
    	
    	
    	
    	// TODO: send password via email
    	String pass = PasswordGenerator.getRandomPassword(5);
    	String hashedPass = PasswordGenerator.hashPassword(pass);
    	
    	newUser.setPassword(hashedPass);
    	newUser.setEnabled(true);
    	newUser.setRoles(userRoleView.getSelectedSet());
    	
    	userService.saveUser(newUser);
    	newUser = new User();
    }

}
