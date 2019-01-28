package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.services.ItemGroupService;

@Component
@Scope("view")
public class ItemGroupListController implements Serializable 
{

	private static final long serialVersionUID = -8259454165251760199L;
	
	@Autowired
	private ItemGroupService groupService;

	public Collection<ItemGroup> getAllItemGroups()
	{
		return groupService.getAllGroups();
	}

}
