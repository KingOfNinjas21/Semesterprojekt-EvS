package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import at.qe.sepm.skeleton.model.Holiday;
import at.qe.sepm.skeleton.services.HolidayService;
import at.qe.sepm.skeleton.utils.ErrorMessage;

public class HolidayDetailController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8777190780509031959L;
	@Autowired
	private HolidayService holidayService;

	@Autowired
	private ErrorMessage errorMessage;

	private Holiday holiday;
	private Holiday newHoliday;

	@PostConstruct
	private void init()
	{
		newHoliday = new Holiday();
	}

	public void doReloadHoliday()
	{
		holiday = holidayService.loadHoliday(holiday.getId());
	}

	/**
	 * Action to save the currently displayed LabItemGroup.
	 */
	public void doSaveHoliday()
	{
		holiday = this.holidayService.saveHoliday(holiday);
	}

	/**
	 * Action to delete the currently displayed LabItemGroup.
	 */
	public void doDeleteHoliday()
	{
		this.holidayService.deleteHoliday(holiday);
		holiday = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddHoliday()
	{

	}
}
