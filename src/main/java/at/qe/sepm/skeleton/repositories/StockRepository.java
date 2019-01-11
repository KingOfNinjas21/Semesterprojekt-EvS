package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.StockItems;

public interface StockRepository extends AbstractRepository<StockItems, Long>
{
	public StockItems findFirstByStockItemId(long id);

}
