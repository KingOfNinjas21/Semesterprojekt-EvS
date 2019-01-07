package at.qe.sepm.skeleton.ui.controllers;

import java.sql.Time;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.services.LabItemService;

@Component
@Scope("view")
public class LabItemController
{
	@Autowired
	private LabItemService labItemService;

	private LabItem labItem;
	private LabItem newLabItem;

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

	public void doReloadLabItem()
	{
		labItem = labItemService.loadLabItem(labItem.getItemId());
	}

	/**
	 * Action to save the currently displayed user.
	 */
	public void doSaveLabItem()
	{
		labItem = this.labItemService.saveLabItem(labItem);
	}

	/**
	 * Action to delete the currently displayed user.
	 */
	public void doDeleteUser()
	{
		this.labItemService.deleteLabItem(labItem);
		labItem = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddLabItem()
	{

		if ((newLabItem.getItemName() == null) || (newLabItem.getItemName().length() <= 3))
		{
			// TODO: return message
			return;
		}

		this.newLabItem.setMaxReservationTime(new Time(100000000));
		this.labItemService.saveLabItem(newLabItem);
		newLabItem = new LabItem();
	}

}
