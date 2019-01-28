package at.qe.sepm.skeleton.ui.view;



import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;




@ManagedBean(name = "reservationView")
@Scope("session")
@Controller
public class ReservationView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9137935247984245761L;
	private boolean onlyShowActive;

	@PostConstruct
	public void init()
	{
		onlyShowActive = false;
	}
	
	public boolean isOnlyShowActive() {
		return onlyShowActive;
	}

	public void setOnlyShowActive(boolean showActive) {
		this.onlyShowActive = showActive;
	}
}
