package at.qe.sepm.skeleton.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@SessionScoped
public class SessionInfoBean {

    public String getCurrentUserName() {
        if (!isLoggedIn()) {
            return "";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return name;
    }

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
        return sb.toString();
    }

    public boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.isAuthenticated() && !auth.getName().equals("anonymousUser");
    }

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

}
