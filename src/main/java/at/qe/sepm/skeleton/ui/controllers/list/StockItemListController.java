package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.StockItemService;

@Component
@Scope("view")
public class StockItemListController implements Serializable 
{

	private static final long serialVersionUID = 8563699962798285320L;
	@Autowired
	private StockItemService stockItemService;

	public Collection<StockItem> getStockItems()
	{
		return stockItemService.loadAll();
	}
}
