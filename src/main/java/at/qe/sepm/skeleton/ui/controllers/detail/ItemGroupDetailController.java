package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import at.qe.sepm.skeleton.model.StockItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.services.ItemGroupService;
import at.qe.sepm.skeleton.ui.view.StockItemView;
import at.qe.sepm.skeleton.utils.ErrorMessage;

@Component
@Scope("view")
public class ItemGroupDetailController implements Serializable 
{

	private static final long serialVersionUID = 287988698966705901L;

	@Autowired
	private ItemGroupService itemGroupService;
	
	
	@Autowired
	private ErrorMessage errorMessage;

	private ItemGroup itemGroup;
	private ItemGroup newItemGroup;
	
	@Autowired
	private StockItemView stockItemView;

	@PostConstruct
	private void init()
	{
		newItemGroup = new ItemGroup();
		newItemGroup.setItems(new TreeSet<StockItem>());
	}

	/**
	 * @return the LabItemGroup
	 */
	public ItemGroup getItemGroup()
	{
		return itemGroup;
	}

	/**
	 * @param LabItemGroup
	 *            the LabItemGroup to set
	 */
	public void setItemGroup(ItemGroup group)
	{
		this.itemGroup = group;
		doReloadItemGroup();
	}

	/**
	 * @return the newLabItemGroup
	 */
	public ItemGroup getNewItemGroup()
	{
		return newItemGroup;
	}

	/**
	 * @param newLabItemGroup
	 *            the newLabItemGroup to set
	 */
	public void setNewItemGroup(ItemGroup newGroup)
	{
		this.newItemGroup = newGroup;
	}

	public void doReloadItemGroup()
	{
		itemGroup = itemGroupService.loadGroup(itemGroup.getGroupId());
	}

	/**
	 * Action to save the currently displayed LabItemGroup.
	 */
	public void doSaveItemGroup()
	{
		itemGroup = this.itemGroupService.saveGroup(itemGroup);
	}

	/**
	 * Action to delete the currently displayed LabItemGroup.
	 */
	public void doDeleteItemGroup()
	{
		this.itemGroupService.deleteGroup(itemGroup);
		itemGroup = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddItemGroup()
	{
		List<StockItem> items = stockItemView.getSelectedItems();
		
		if ((newItemGroup.getGroupName() == null) || (newItemGroup.getGroupName().length() <= 3))
		{
			errorMessage.pushMessage("Group name invalid.");
			return;
		}
		
		if ((items == null) || items.isEmpty())
		{
			errorMessage.pushMessage("Item selection invalid");
			return;
		}
		
		newItemGroup.setItems(new TreeSet<StockItem>(items));
		
		itemGroupService.saveGroup(newItemGroup);
		newItemGroup = new ItemGroup();
		stockItemView.setSelectedItems(null);
	}

}
