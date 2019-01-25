package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.services.LabItemService;
import at.qe.sepm.skeleton.utils.ErrorMessage;

@Component
@Scope("view")
public class LabItemDetailController implements Serializable 
{

	private static final long serialVersionUID = 152811281487490741L;

	@Autowired
	private LabItemService labItemService;
	
	@Autowired
	private ErrorMessage errorMessage;

	private LabItem labItem;
	private LabItem newLabItem;
	private int days	= 0;
	private int hours	= 0;
	private int minutes	= 0;

	@PostConstruct
	private void init()
	{
		newLabItem = new LabItem();
	}

	/**
	 * @return the labItem
	 */
	public LabItem getLabItem()
	{
		return labItem;
	}

	/**
	 * @param labItem
	 *            the labItem to set
	 */
	public void setLabItem(LabItem labItem)
	{
		this.labItem = labItem;
		doReloadLabItem();
	}

	/**
	 * @return the newLabItem
	 */
	public LabItem getNewLabItem()
	{
		return newLabItem;
	}

	/**
	 * @param newLabItem
	 *            the newLabItem to set
	 */
	public void setNewLabItem(LabItem newLabItem)
	{
		this.newLabItem = newLabItem;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void doReloadLabItem()
	{
		labItem = labItemService.loadLabItem(labItem.getItemId());
	}

	/**
	 * Action to save the currently displayed labItem.
	 */
	public void doSaveLabItem()
	{
		labItem = this.labItemService.reSaveLabItem(labItem);

	}

	/**
	 * Action to delete the currently displayed labItem.
	 */
	public void doDeleteLabItem()
	{
		this.labItemService.deleteLabItem(labItem);
		labItem = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddLabItem()
	{
		if ((newLabItem.getItemName() == null) || (newLabItem.getItemName().length() <= 3))
		{
			errorMessage.setMessage("Item Name invalid.");
			errorMessage.pushMessage();
			return;
		}
		
		
		if (newLabItem.getMaxReservationTime() == null ) {
			if (days + hours + minutes <= 0) {
				errorMessage.setMessage("Max Reservation Time invalid.");
				errorMessage.pushMessage();
				return;
			}
		}
		
		
		if (errorMessage.hasError()) {
			return;
		}
		

		Period period = new Period().withDays(days)
				.withHours(hours)
				.withMinutes(minutes);

		newLabItem.setMaxReservationTime(period);
		labItem = labItemService.saveLabItem(newLabItem);
		newLabItem = new LabItem();
	}

}
