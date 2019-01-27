package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@ManagedBean(name = "holidayView")
@Scope("session")
@Controller
public class HolidayView implements Serializable 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2384383398093090647L;

	private boolean onlyShowFuture;
	
	@PostConstruct
	public void init()
	{
		setOnlyShowFuture(true);
	}

	public boolean getOnlyShowFuture() {
		return onlyShowFuture;
	}

	public void setOnlyShowFuture(boolean onlyShowFuture) {
		this.onlyShowFuture = onlyShowFuture;
	}
}