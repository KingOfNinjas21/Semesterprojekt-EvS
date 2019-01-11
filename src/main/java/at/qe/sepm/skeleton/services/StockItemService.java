package at.qe.sepm.skeleton.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.StockItems;
import at.qe.sepm.skeleton.repositories.StockRepository;

@Component
@Scope("application")
public class StockItemService
{
	@Autowired
	private StockRepository stockRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public StockItems loadStockItem(long id)
	{
		return stockRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public List<StockItems> loadAll()
	{
		return stockRepository.findAll();
	}

	public StockItems loadByName(long id)
	{
		return stockRepository.findFirstByStockItemId(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public StockItems saveStockItem(StockItems stockItem)
	{
		return stockRepository.save(stockItem);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void saveMultipleStockItems(StockItems stockItem, int count)
	{
		for (int i = 0; i < count; i++)
		{
			stockRepository.save(stockItem);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteStockItem(StockItems stockItem)
	{

		stockRepository.delete(stockItem);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Collection<StockItems> getAllStockItems()
	{

		return stockRepository.findAll();
	}

}
