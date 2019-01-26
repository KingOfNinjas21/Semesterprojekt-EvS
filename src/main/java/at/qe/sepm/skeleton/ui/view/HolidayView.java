package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.Holiday;
import at.qe.sepm.skeleton.repositories.HolidayRepository;


@ManagedBean(name = "openingHourView")
@Scope("view")
@Controller
public class HolidayView implements Serializable 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2384383398093090647L;

	@Autowired
	private HolidayRepository holidayRepository;

	private List<Holiday> holidays;
	private List<Holiday> selectedHolidays;

	@PostConstruct
	public void init()
	{
		holidays = new ArrayList<Holiday>();
		selectedHolidays = new ArrayList<Holiday>();

		holidays.addAll(holidayRepository.findAll());
	}

	public List<Holiday> getHolidays()
	{
		return holidays;
	}

	public List<Holiday> getSelectedHolidays()
	{
		return selectedHolidays;
	}

	public void setSelectedOpeningHours(List<Holiday> selectedHolidays)
	{
		this.selectedHolidays = selectedHolidays;
	}

}