package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.repositories.LabItemGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * LabItemGroupService to find the groups
 * @author fbn
 */

@Component
@Scope("application")
public class LabItemGroupService {
    @Autowired
    private LabItemGroupRepository labitemgroup;

    @PreAuthorize("hasAuthority('ADMIN')")
    public LabItemGroup loadLabItemGroup(long id)
    {
        return labitemgroup.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<LabItemGroup> loadAll()
    {
        return labitemgroup.findAll();
    }

}
