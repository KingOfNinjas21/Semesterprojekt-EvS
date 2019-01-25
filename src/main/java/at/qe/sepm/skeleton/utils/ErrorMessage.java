package at.qe.sepm.skeleton.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class ErrorMessage {

	
	private String message = "";
	FacesContext context;
	

	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void reset() {
		message = "";
	}
     
	public void getFacesContext()
	{
		context = FacesContext.getCurrentInstance();
	}
	
    public void pushMessage() {
        
    	context.validationFailed();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + message, "" ) );
    }
    
    public boolean hasError() {
    	return context.isValidationFailed();
    }
}
