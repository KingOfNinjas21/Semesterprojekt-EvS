package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.StockItem;

public interface StockRepository extends AbstractRepository<StockItem, Long>
{
	public StockItem findFirstByStockItemId(long id);

}
