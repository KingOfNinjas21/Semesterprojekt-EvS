package at.qe.sepm.skeleton.ui.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;
import at.qe.sepm.skeleton.services.StockItemService;

@Component
@Scope("view")
public class StockItemDetailController
{
	@Autowired
	private StockItemService stockItemService;

	private StockItem stockItem;
	private StockItem newStockItem;
	private LabItemRepository lab;

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
		this.stockItemService.saveMultipleStockItems(newStockItem, number);
		newStockItem = new StockItem();
	}
}
