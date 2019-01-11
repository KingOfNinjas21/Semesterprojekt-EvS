package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.StockItems;
import at.qe.sepm.skeleton.repositories.StockRepository;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "labItemView")
@Scope("view")
@Controller
public class LabItemView
{

	@Autowired
	private StockRepository stockRepository;

	private List<StockItems> items;
	private List<StockItems> selectedItems;

	@PostConstruct
	public void init()
	{
		items = new ArrayList<StockItems>();
		selectedItems = new ArrayList<StockItems>();

		items.addAll(stockRepository.findAll());
	}

	public List<StockItems> getItems()
	{
		return items;
	}

	public List<StockItems> getSelectedItems()
	{
		return selectedItems;
	}

	public void setSelectedItems(List<StockItems> selectedLabItems)
	{
		this.selectedItems = selectedLabItems;
	}

}