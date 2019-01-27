package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.ItemGroupService;

import org.joda.time.DateTime;
import org.joda.time.Period;
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

	private Collection<StockItem> items;

	private Collection<StockItem> selectedItems;

	private Collection<ItemGroup> itemGroups;

	private Collection<ItemGroup> selectedItemGroups;
	
	private boolean onlyShowAvailable = false;
	
	@PostConstruct
	public void init()
	{
		items = new ArrayList<StockItem>();
		selectedItems = new ArrayList<StockItem>();
		itemGroups = itemGroupService.getAllGroups();
		loadItemsNotBlocked();			
	}
	
	public boolean getOnlyShowAvailable() {
		return onlyShowAvailable;
	}

	
	public void setOnlyShowAvailable(boolean onlyShowAvailable) {
		this.onlyShowAvailable = onlyShowAvailable;
	}

	private void loadItemsNotBlocked()
	{
		for (StockItem item : stockItemService.loadAll())
		{
			if (item.isBlocked())
				continue;
			else
				items.add(item);
		}
	}
	
	private void filterItemsNotAvailable()
	{
		
		Iterator<StockItem> iter = items.iterator();

		while (iter.hasNext()) {
			StockItem item = iter.next();
		    if (isAvailable(item, calendarView.getBeginDate(), calendarView.getEndDate()))
		        iter.remove();
		}
		
	}
	
	public boolean isAvailable(StockItem item, Date from, Date to)
	{
		DateTime dt = new DateTime(from);
		Period period = item.getLabItem().getMaxReservationTime();
		
		if (period == null) {
			throw new NullPointerException("getMaxReservationTime is null");
		}
		
		if (dt.plus(period).isBefore(to.getTime()) ) {
			return false;
		}

		for (Reservation reservation : item.getReservations()) {

			if (reservation.getIsReturned()) {
				continue;
			}
			if (from.before(reservation.getReservationDate()) && to.before(reservation.getReservationDate())) {
				continue;
			}
			if (from.after(reservation.getReturnableDate()) && to.after(reservation.getReturnableDate())) {
				continue;
			}
			return false;
		}

		return true;
	}
	
	
	public Collection<StockItem> getItems()
	{
		items = new ArrayList<StockItem>();
		loadItemsNotBlocked();
		if(onlyShowAvailable)
			{
				filterItemsNotAvailable();
			}
		return items;
	}

	public void getItemsFromSelectedGroups(Collection<ItemGroup> itemGroups){
		for(ItemGroup group: itemGroups){
			selectedItems.addAll(group.getItems());
		}
	}

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