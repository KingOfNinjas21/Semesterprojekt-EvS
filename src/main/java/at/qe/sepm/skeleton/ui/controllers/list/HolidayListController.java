package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import at.qe.sepm.skeleton.model.Holiday;
import at.qe.sepm.skeleton.services.HolidayService;

public class HolidayListController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4121285309302533648L;
	@Autowired
	private HolidayService holidayService;

	public Collection<Holiday> getAllHolidays()
	{
		return holidayService.loadAll();
	}
}
