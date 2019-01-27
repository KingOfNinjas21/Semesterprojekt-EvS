package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.LabItem;

public interface LabItemRepository extends AbstractRepository<LabItem, Long>
{

	public LabItem findFirstByItemId(long id);

	public LabItem findFirstByItemName(String name);

}
