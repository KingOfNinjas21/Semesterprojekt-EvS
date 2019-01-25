package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.services.LabItemService;

@Component
@Scope("view")
public class LabItemListController implements Serializable 
{
	
	private static final long serialVersionUID = 4440569332963775007L;
	
	@Autowired
	private LabItemService labItemService;

	public Collection<LabItem> getLabItems()
	{
		return labItemService.loadAll();
	}

}
