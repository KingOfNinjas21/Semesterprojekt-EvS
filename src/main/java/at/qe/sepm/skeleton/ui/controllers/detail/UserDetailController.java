package at.qe.sepm.skeleton.ui.controllers.detail;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.services.EmailService;
import at.qe.sepm.skeleton.services.UserService;
import at.qe.sepm.skeleton.ui.view.UserRolesView;
import at.qe.sepm.skeleton.utils.ErrorMessage;
import at.qe.sepm.skeleton.utils.PasswordGenerator;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
public class UserDetailController implements Serializable {

	private static final long serialVersionUID = 1377920198925965329L;

	@Autowired
    private UserService userService;
    
    @Autowired
    private UserRolesView userRoleView;
    
    @Autowired
    private ErrorMessage errorMessage;
    
    @Autowired
    private EmailService emailService;

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

    /**
     * Returns the new user.
     *
     * @return
     */
    public User getNewUser() {
		return newUser;
	}

    /**
     * Sets the new user.
     *
     * @param user
     */
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
   
    /**
     * Action to add a new user.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public void doAddUser() {
    	
    	if (newUser.getUsername().length() <= 3) {
    		errorMessage.pushMessage("Username not valid");
    		return;
    	}
    	
    	
    	if (newUser.getFirstName() == "") {
    		errorMessage.pushMessage("Firstname not valid");
    		return;
    	}
    	
    	if (newUser.getLastName() == "") {
    		errorMessage.pushMessage("Lastname not valid");
    		return;
    	}
    	
    	String email = newUser.getEmail();    	
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			errorMessage.pushMessage("Email not validl!");
			return;
		}
    	
		newUser.setRoles(userRoleView.getSelectedSet());
    	if (newUser.getRoles() == null) {
    		errorMessage.pushMessage("No user authorization selected");
    		return;
    	}
    
    	
    	String pass = PasswordGenerator.getRandomPassword(5);
    	String hashedPass = PasswordGenerator.hashPassword(pass);
    	
    	newUser.setPassword(hashedPass);
    	newUser.setEnabled(true);
    	
    	
    	if (userService.saveUser(newUser) != null) {
    		emailService.sendPassword(pass);
    	}
    	
    	newUser = new User();
    	errorMessage.reset();
    }

}
