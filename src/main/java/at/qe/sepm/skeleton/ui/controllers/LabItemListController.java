package at.qe.sepm.skeleton.ui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.services.LabItemService;

@Component
@Scope("view")
public class LabItemListController
{
	@Autowired
	private LabItemService labItemService;

	public Collection<LabItem> getLabItems()
	{
		return labItemService.getAllLabItems();
	}

}
