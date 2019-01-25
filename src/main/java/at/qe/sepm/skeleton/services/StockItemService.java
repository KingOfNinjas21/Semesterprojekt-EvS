package at.qe.sepm.skeleton.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.repositories.StockRepository;

@Service
@Scope("application")
public class StockItemService
{
	@Autowired
	private StockRepository stockRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public StockItem loadStockItem(long id)
	{
		return stockRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public List<StockItem> loadAll()
	{
		return stockRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public StockItem saveStockItem(StockItem stockItem)
	{
		return stockRepository.save(stockItem);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void saveMultipleStockItems(StockItem stockItem, int count)
	{
		for (int i = 0; i < count; i++)
		{
			stockRepository.save(stockItem);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteStockItem(StockItem stockItem)
	{

		stockRepository.delete(stockItem);
	}


}
