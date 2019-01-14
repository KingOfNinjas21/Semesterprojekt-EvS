package at.qe.sepm.skeleton.ui.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.StockItemService;
import at.qe.sepm.skeleton.utils.LabItemView;

@Component
@Scope("view")
public class StockItemDetailController
{
	@Autowired
	private StockItemService stockItemService;

	private StockItem stockItem;
	private StockItem newStockItem;
	@Autowired
	private LabItemView labView;

	private int number = 1;

	@PostConstruct
	private void init()
	{
		newStockItem = new StockItem();
	}

	/**
	 * @return the stockItem
	 */
	public StockItem getStockItem()
	{
		return stockItem;
	}

	/**
	 * @param stockItem
	 *            the stockItem to set
	 */
	public void setStockItem(StockItem stockItem)
	{
		this.stockItem = stockItem;
	}

	/**
	 * @return the newStockItem
	 */
	public StockItem getNewStockItem()
	{
		return newStockItem;
	}

	/**
	 * @param newStockItem
	 *            the newStockItem to set
	 */
	public void setNewStockItem(StockItem newStockItem)
	{
		this.newStockItem = newStockItem;
	}

	/**
	 * @return the number of elements you want to creat
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *            the number to set of elements you want to creat
	 */
	public void setNumber(int number)
	{
		this.number = number;
	}

	public void doReloadStockItem()
	{
		stockItem = stockItemService.loadStockItem(stockItem.getStockItemId());
	}

	/**
	 * Action to save the currently displayed stockItem.
	 */
	public void doSaveStockItem()
	{
		this.stockItemService.saveStockItem(stockItem);
	}

	/**
	 * Action to delete the currently displayed user.
	 */
	public void doDeleteStockItem()
	{
		this.stockItemService.deleteStockItem(stockItem);
		stockItem = null;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doAddStockItem()
	{
		// LabItem item = newStockItem.getLabItem();
		List<LabItem> selectedItem = labView.getSelectedItem();
		String laboratory = newStockItem.getLabName();
		String location = newStockItem.getLocation();

		if (selectedItem.isEmpty())
		{
			return;
		}

		if (laboratory == null)
		{
			return;
		}

		if (location == null)
		{
			return;
		}

		newStockItem.setLabItem(labView.getSelectedItem().get(0));
		this.stockItemService.saveMultipleStockItems(newStockItem, number);
		number = 1;
		newStockItem = new StockItem();
	}
}
