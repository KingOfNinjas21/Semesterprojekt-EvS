package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Holiday;
import at.qe.sepm.skeleton.services.HolidayService;

@Component
@Scope("view")
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
	
	public Collection<Holiday> getSortedByBeginDate()
	{
		ArrayList<Holiday> holidays = new ArrayList<Holiday>(holidayService.loadAll());
		Collections.sort(holidays, new Comparator<Holiday>() {
	        @Override
	        public int compare(Holiday holiday1, Holiday holiday2)
	        {

	            return  holiday1.getBeginDate().compareTo(holiday2.getBeginDate());
	        }
	    });
		
		return holidays;
	}
}
