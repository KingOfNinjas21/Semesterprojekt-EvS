package at.qe.sepm.skeleton.ui.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.StockItems;
import at.qe.sepm.skeleton.services.StockItemService;

@Component
@Scope("view")
public class StockItemListController
{

	@Autowired
	private StockItemService stockItemService;

	public Collection<StockItems> getLabItems()
	{
		return stockItemService.getAllStockItems();
	}

}
