package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.model.Reservation;
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
	
	@Autowired
	private CalendarView calendarView;
	
	private Collection<StockItem> allItems;
	
	private Collection<StockItem> items;
	private Collection<StockItem> selectedItems;
	
	private Collection<ItemGroup> itemGroups;
	private Collection<ItemGroup> selectedItemGroups;
	
	
	private boolean onlyShowAvailable = true;
	
	@PostConstruct
	public void init()
	{
		allItems = new ArrayList<StockItem>();	
		items = new ArrayList<StockItem>();
		selectedItems = new ArrayList<StockItem>();
		itemGroups = new ArrayList<ItemGroup>();
		selectedItemGroups = new ArrayList<ItemGroup>();
		
		
		loadItems();
		loadItemGroups();
	}


	public void loadItems()	{
		allItems = stockItemService.loadAllNonBlocked();
		items.addAll(allItems);
	}
	
	public void loadItemGroups() {
		itemGroups = itemGroupService.getAllGroups();
	}
	
	
	public void filterItemsNotAvailable()
	{
		if (!onlyShowAvailable)
			return;
		
		selectedItems.clear();
		items.clear();

		for (StockItem item : allItems) {
			
		    if (isAvailable(item, calendarView.getBeginDate(), calendarView.getEndDate()))
		    	items.add(item);
		}
		
	}
	
	private boolean isAvailable(StockItem item, Date from, Date to)
	{
		// Check if there is a reservation between from and to
		for (Reservation reservation : item.getReservations()) {
			Date begin = reservation.getReservationDate();
			Date end = reservation.getReturnableDate();
			

			if( end.after(to) && begin.before(from) ||
				to.after(begin) && from.before(begin) ||
				from.before(end) && to.after(end) ||
				from.before(begin) && to.after(end)){
				
				return false;
			}
		}

		return true;
	}
	
	
	public boolean getOnlyShowAvailable() {
		return onlyShowAvailable;
	}

	
	public void setOnlyShowAvailable(boolean onlyShowAvailable) {
		if (onlyShowAvailable)
			filterItemsNotAvailable();
		this.onlyShowAvailable = onlyShowAvailable;
	}
	
	
	public Collection<StockItem> getItems()
	{
		return items;
	}

//	public void getItemsFromSelectedGroups(Collection<ItemGroup> itemGroups){
//		for(ItemGroup group: itemGroups){
//			selectedItems.addAll(group.getItems());
//		}
//	}

	public Collection<StockItem> getSelectedItems() {
		
		if (selectedItemGroups != null) {
			if (!selectedItemGroups.isEmpty()) {
				for (ItemGroup item : selectedItemGroups) {
					selectedItems.addAll(item.getItems());
				}
			}
		}
			
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

	public Collection<ItemGroup> getSelectedItemGroups() {
		return selectedItemGroups;
	}

	public void setSelectedItemGroups(List<ItemGroup> selectedItemGroups) {
		this.selectedItemGroups = selectedItemGroups;
	}
}