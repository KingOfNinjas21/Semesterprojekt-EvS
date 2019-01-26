package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;
import at.qe.sepm.skeleton.services.LabItemService;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "labItemView")
@Scope("view")
public class LabItemView implements Serializable 
{

	private static final long serialVersionUID = -2990342781351510303L;

	@Autowired
	private LabItemService labItemService;

	private List<LabItem> items;
	private List<LabItem> selectedItems;

	@PostConstruct
	public void init()
	{
		items = new ArrayList<LabItem>();
		selectedItems = new ArrayList<LabItem>();

		items.addAll(labItemService.loadAll());
	}

	public List<LabItem> getItems()
	{
		return items;
	}

	public List<LabItem> getSelectedItems()
	{
		return selectedItems;
	}

	public void setSelectedItems(List<LabItem> selectedLabItems)
	{
		this.selectedItems = selectedLabItems;
	}

}