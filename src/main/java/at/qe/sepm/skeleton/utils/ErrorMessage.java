package at.qe.sepm.skeleton.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("view")
@Component
public class ErrorMessage {

	
	private boolean enabled = false;
	private String message = "";
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		enabled = true;
		this.message = message;
	}
	
	
	
	
}
