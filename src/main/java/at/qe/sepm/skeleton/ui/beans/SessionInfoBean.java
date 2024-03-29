package at.qe.sepm.skeleton.ui.beans;

import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.model.UserRole;
import at.qe.sepm.skeleton.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Session information bean to retrieve session-specific parameters.
 *
 * This class is part of the skeleton project provided for students of the
 * course "Softwaredevelopment and Project Management" offered by the University
 * of Innsbruck.
 */
@Component
@Scope("session")
public class SessionInfoBean {

    @Autowired
    private UserService userService;

    /**
     * Attribute to cache the current user.
     */
    private User currentUser;

    /**
     * Returns the currently logged on user, null if no user is authenticated
     * for this session.
     *
     * @return
     */
    public User getCurrentUser() {
        if (currentUser == null) {
            String currentUserName = getCurrentUserName();
            if (currentUserName.isEmpty()) {
                return null;
            }
            currentUser = userService.loadUser(currentUserName);
        }
        return currentUser;
    }

    /**
     * Returns the username of the user for this session, empty string if no
     * user has been authenticated for this session.
     *
     * @return
     */
    public String getCurrentUserName() {
        if (!isLoggedIn()) {
            return "";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return name;
    }

    /**
     * Returns the roles of the user for this session as space-separated list,
     * empty string if no user has been authenticated for this session-
     *
     * @return
     */
    public String getCurrentUserRoles() {
        if (!isLoggedIn()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority role : auth.getAuthorities()) {
            sb.append(role.getAuthority());
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * Checks if a user is authenticated for this session.
     *
     * @return true if a non-anonymous user has been authenticated, false
     * otherwise
     */
    public boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.isAuthenticated() && !auth.getName().equals("anonymousUser");
        } else {
            return false;
        }
    }

    /**
     * Checks if the user for this session has the given role (c.f.
     * {@link UserRole}).
     *
     * @param role the role to check for as string
     * @return true if a user is authenticated and the current user has the
     * given role, false otherwise
     */
    public boolean hasRole(String role) {
        if (role == null || role.isEmpty() || !isLoggedIn()) {
            return false;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority ga : auth.getAuthorities()) {
            if (role.equals(ga.getAuthority())) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Checks if the user for this session has the role Student (c.f.
     * {@link UserRole}).
     *
     * @return true if a user is authenticated and the current user has the
     * given role, false otherwise
     */
    public boolean isStudent() {
    	return hasRole("STUDENT");
    }
    
    /**
     * Checks if the user for this session has the role Employee (c.f.
     * {@link UserRole}).
     *
     * @return true if a user is authenticated and the current user has the
     * given role, false otherwise
     */
    public boolean isEmployee() {
    	return hasRole("EMPLOYEE");
    }
    
    /**
     * Checks if the user for this session has the role Admin (c.f.
     * {@link UserRole}).
     *
     * @return true if a user is authenticated and the current user has the
     * given role, false otherwise
     */
    public boolean isAdmin() {
    	return hasRole("ADMIN");
    }

}
