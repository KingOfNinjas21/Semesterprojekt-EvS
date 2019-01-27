package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Holiday;
import at.qe.sepm.skeleton.services.HolidayService;

@Component
@Scope("view")
public class HolidayDetailController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8777190780509031959L;
	@Autowired
	private HolidayService holidayService;

	//@Autowired
	//private ErrorMessage errorMessage;

	private Holiday holiday;
	private Holiday newHoliday;
	
	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	public Holiday getNewHoliday() {
		return newHoliday;
	}

	public void setNewHoliday(Holiday newHoliday) {
		this.newHoliday = newHoliday;
	}

	

	@PostConstruct
	private void init()
	{
		newHoliday = new Holiday();
	}

	public void doReloadHoliday()
	{
		holiday = holidayService.loadHoliday(holiday.getId());
	}


	public void doSaveHoliday()
	{
		holiday = this.holidayService.saveHoliday(holiday);
	}

	public void doDeleteHoliday()
	{
		this.holidayService.deleteHoliday(holiday);
		holiday = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddHoliday()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(newHoliday.getEndDate());
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		newHoliday.setEndDate(cal.getTime());
		this.holidayService.saveHoliday(newHoliday);
		newHoliday = null;
	}
}
