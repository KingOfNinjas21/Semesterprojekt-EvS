package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.model.User;

import java.util.Collection;

/**
 * @author fbn
 */

public interface ItemGroupRepository extends AbstractRepository<ItemGroup, Long> {

    public Collection<ItemGroup> findByUser(User user);

}
