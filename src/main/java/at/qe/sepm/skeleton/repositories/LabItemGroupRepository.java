package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.model.User;

import java.util.Collection;

/**
 * @author fbn
 */

public interface LabItemGroupRepository extends AbstractRepository<LabItemGroup, Long> {

    public Collection<LabItemGroup> findByUser(User user);

}
