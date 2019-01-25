package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import at.qe.sepm.skeleton.model.UserRole;


/**
 * A user roles view. Loads the items from {@code UserRole}. 
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "userRolesView")
@Scope("view")
@Controller
public class UserRolesView implements Serializable {
	
	private static final long serialVersionUID = 8258865753603802548L;
	private UserRole[] selected;


	public UserRole[] getRoles() {
		return UserRole.values();
	}


	public UserRole[] getSelected() {
		return selected;
	}
	
	public Set<UserRole> getSelectedSet() {
		return new HashSet<UserRole>(Arrays.asList(selected));
	}


	public void setSelected(UserRole[] selectedRoles) {
		this.selected = selectedRoles;
	}


}
