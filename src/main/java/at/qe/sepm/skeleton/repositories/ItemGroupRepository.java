package at.qe.sepm.skeleton.repositories;

import java.util.Collection;

import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.model.User;

/**
 * @author fbn
 */

public interface ItemGroupRepository extends AbstractRepository<ItemGroup, Long>
{

	public Collection<ItemGroup> findByUser(User user);

}
