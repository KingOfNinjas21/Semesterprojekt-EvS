package at.qe.sepm.skeleton.ui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.LabItemGroupService;

@Component
@Scope("view")
public class LabItemGroupController
{
	@Autowired
	private LabItemGroupService labItemGroupService;

	public Collection<LabItemGroup> getLabItemGroups()
	{
		return labItemGroupService.getAllLabItemGroups();
	}

}
