package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import at.qe.sepm.skeleton.model.ItemCondition;
import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.services.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.StockItemService;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "stockItemView")
@Scope("view")
@Controller
public class StockItemView implements Serializable 
{

	private static final long serialVersionUID = -6659257695533986758L;
	
	@Autowired
	private StockItemService stockItemService;

	@Autowired
	private ItemGroupService itemGroupService;

	private List<StockItem> items;

	private List<StockItem> selectedItems;

	private Collection<ItemGroup> itemGroups;

	private List<ItemGroup> selectedItemGroups;

	@PostConstruct
	public void init()
	{
		items = new ArrayList<StockItem>();
		selectedItems = new ArrayList<StockItem>();
		itemGroups = itemGroupService.getAllGroups();

		for (StockItem item : stockItemService.loadAll())
		{
			if (item.isBlocked())
				continue;
			else
				items.add(item);
		}
	}

	public List<StockItem> getItems()
	{
		return items;
	}

	public List<StockItem> getSelectedItems()
	{
		return selectedItems;
	}

	public void setSelectedItems(List<StockItem> selectedLabItems)
	{
		this.selectedItems = selectedLabItems;
	}

	public void setItems(List<StockItem> items) {
		this.items = items;
	}

	public Collection<ItemGroup> getItemGroups() {
		return itemGroups;
	}

	public void setItemGroups(Collection<ItemGroup> itemGroups) {
		this.itemGroups = itemGroups;
	}

	public List<ItemGroup> getSelectedItemGroups() {
		return selectedItemGroups;
	}

	public void setSelectedItemGroups(List<ItemGroup> selectedItemGroups) {
		this.selectedItemGroups = selectedItemGroups;
	}
	
}