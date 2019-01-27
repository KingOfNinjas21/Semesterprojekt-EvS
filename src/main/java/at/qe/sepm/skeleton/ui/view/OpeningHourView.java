package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@ManagedBean(name = "openingHourView")
@Scope("view")
@Controller
public class OpeningHourView implements Serializable 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1450030318846219581L;

	
	private List<String> weekdays;

	public List<String> getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(List<String> weekdays) {
		this.weekdays = weekdays;
	}

	@PostConstruct
	public void init()
	{
		weekdays = new ArrayList<String>();
		weekdays.add("Monday");
		weekdays.add("Tuesday");
		weekdays.add("Wednesday");
		weekdays.add("Thursday");
		weekdays.add("Friday");
		weekdays.add("Saturday");
		weekdays.add("Sunday");
		

	}


}