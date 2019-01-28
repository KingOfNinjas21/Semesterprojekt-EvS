package at.qe.sepm.skeleton.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.model.StockItem;

public interface StockRepository extends AbstractRepository<StockItem, Long>
{
	public StockItem findFirstByStockItemId(long id);

	@Query("SELECT s FROM StockItem s WHERE s.blocked = FALSE")
	public List<StockItem> loadAllNonBlocked();

	public Collection<StockItem> findAllByLabItem(LabItem labItem);
}
