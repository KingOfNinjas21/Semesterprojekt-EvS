package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.repositories.StockRepository;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "stockItemView")
@Scope("view")
@Controller
public class StockItemView
{

	@Autowired
	private StockRepository stockRepository;

	private List<StockItem> items;
	private List<StockItem> selectedItems;

	@PostConstruct
	public void init()
	{
		items = new ArrayList<StockItem>();
		selectedItems = new ArrayList<StockItem>();

		for (StockItem item : stockRepository.findAll())
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

}