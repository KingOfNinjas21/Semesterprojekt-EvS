package at.qe.sepm.skeleton.utils;

import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class ErrorMessage {

	
	private boolean enabled = false;
	private String message = "";
	
	
	public boolean getEnabled() {
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
	
	public void reset() {
		enabled = false;
		message = "";
	}
}
