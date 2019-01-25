package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.services.LabItemGroupService;

@Component
@Scope("view")
public class LabItemGroupListController implements Serializable 
{

	private static final long serialVersionUID = -8259454165251760199L;
	
	@Autowired
	private LabItemGroupService labItemGroupService;

	public Collection<LabItemGroup> getLabItemGroups()
	{
		return labItemGroupService.getAllLabItemGroups();
	}

}
