package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.OpeningHour;
import at.qe.sepm.skeleton.services.OpeningHourService;

@Component
@Scope("view")
public class OpeningHourDetailController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 84312092343628326L;

	@Autowired
	private OpeningHourService openingHourService;

	//@Autowired
	//private ErrorMessage errorMessage;

	private OpeningHour openingHour;
	private OpeningHour newOpeningHour;
	
	public OpeningHour getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(OpeningHour openingHour) {
		this.openingHour = openingHour;
	}

	public OpeningHour getNewOpeningHour() {
		return newOpeningHour;
	}

	public void setNewOpeningHour(OpeningHour newOpeningHour) {
		this.newOpeningHour = newOpeningHour;
	}


	@PostConstruct
	private void init()
	{
		newOpeningHour = new OpeningHour();
	}

	public void doReloadOpeningHour()
	{
		openingHour = openingHourService.loadOpeningHour(openingHour.getId());
	}

	/**
	 * Action to save the currently displayed LabItemGroup.
	 */
	public void doSaveOpeningHour()
	{
		openingHour = this.openingHourService.saveOpeningHour(openingHour);
	}

	/**
	 * Action to delete the currently displayed LabItemGroup.
	 */
	public void doDeleteOpeningHour()
	{
		this.openingHourService.deleteOpeningHour(openingHour);
		openingHour = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddOpeningHour()
	{
		this.openingHourService.saveOpeningHour(newOpeningHour);
		newOpeningHour = null;
	}
}
