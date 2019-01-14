package at.qe.sepm.skeleton.ui.controllers;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import at.qe.sepm.skeleton.model.LabItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.services.LabItemGroupService;

@Component
@Scope("view")
public class LabItemGroupDetailController
{
	@Autowired
	private LabItemGroupService LabItemGroupService;

	private LabItemGroup labItemGroup;
	private LabItemGroup newLabItemGroup;

	@PostConstruct
	private void init()
	{
		newLabItemGroup = new LabItemGroup();
		newLabItemGroup.setLabitem(new ArrayList<LabItem>());
	}

	/**
	 * @return the LabItemGroup
	 */
	public LabItemGroup getLabItemGroup()
	{
		return labItemGroup;
	}

	/**
	 * @param LabItemGroup
	 *            the LabItemGroup to set
	 */
	public void setLabItemGroup(LabItemGroup LabItemGroup)
	{
		this.labItemGroup = LabItemGroup;
		doReloadLabItemGroup();
	}

	/**
	 * @return the newLabItemGroup
	 */
	public LabItemGroup getNewLabItemGroup()
	{
		return newLabItemGroup;
	}

	/**
	 * @param newLabItemGroup
	 *            the newLabItemGroup to set
	 */
	public void setNewLabItemGroup(LabItemGroup newLabItemGroup)
	{
		this.newLabItemGroup = newLabItemGroup;
	}

	public void doReloadLabItemGroup()
	{
		labItemGroup = LabItemGroupService.loadLabItemGroup(labItemGroup.getGroupId());
	}

	/**
	 * Action to save the currently displayed LabItemGroup.
	 */
	public void doSaveLabItemGroup()
	{
		labItemGroup = this.LabItemGroupService.saveLabItemGroup(labItemGroup);
	}

	/**
	 * Action to delete the currently displayed LabItemGroup.
	 */
	public void doDeleteLabItemGroup()
	{
		this.LabItemGroupService.deleteLabItemGroup(labItemGroup);
		labItemGroup = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddLabItemGroup()
	{

		if ((newLabItemGroup.getGroupName() == null) || (newLabItemGroup.getGroupName().length() <= 3))
		{
			// TODO: return message
			return;
		}

		this.LabItemGroupService.saveLabItemGroup(newLabItemGroup);
		newLabItemGroup = new LabItemGroup();
	}

}
