package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.OpeningHour;
import at.qe.sepm.skeleton.repositories.OpeningHourRepository;


@ManagedBean(name = "openingHourView")
@Scope("view")
@Controller
public class OpeningHourView implements Serializable 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1450030318846219581L;

	@Autowired
	private OpeningHourRepository openingHourRepository;

	private List<OpeningHour> openingHours;
	private List<OpeningHour> selectedOpeningHours;

	@PostConstruct
	public void init()
	{
		openingHours = new ArrayList<OpeningHour>();
		selectedOpeningHours = new ArrayList<OpeningHour>();

		openingHours.addAll(openingHourRepository.findAll());
	}

	public List<OpeningHour> getOpeningHours()
	{
		return openingHours;
	}

	public List<OpeningHour> getSelectedOpeningHours()
	{
		return selectedOpeningHours;
	}

	public void setSelectedOpeningHours(List<OpeningHour> selectedOpeningHours)
	{
		this.selectedOpeningHours = selectedOpeningHours;
	}

}