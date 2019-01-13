package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.repositories.LabItemGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;

import java.util.Collection;
import java.util.List;

/**
 * LabItemGroupService to find the groups
 * @author fbn
 */

@Component
@Scope("application")
public class LabItemGroupService {
	@Autowired
	private SessionInfoBean sessionInfo;
	
    @Autowired
    private LabItemGroupRepository labItemGroupRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public LabItemGroup loadLabItemGroup(long id)
    {
        return labItemGroupRepository.findOne(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<LabItemGroup> getAllLabItemGroups()
    {
        return labItemGroupRepository.findAll();
    }
    
	@PreAuthorize("hasAuthority('ADMIN')")
	public LabItemGroup saveLabItemGroup(LabItemGroup labItemGroup)
	{
		return labItemGroupRepository.save(labItemGroup);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteLabItemGroup(LabItemGroup labItemGroup)
	{

		labItemGroupRepository.delete(labItemGroup);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STUDENT') || hasAuthority('EMPLOYEE')")
	public Collection<LabItemGroup> getLabItemGroupsByUser()
	{
		Collection<LabItemGroup> allGroups = labItemGroupRepository.findAll();
		allGroups.removeIf(g -> g.getUser().getUsername() != sessionInfo.getCurrentUserName());
		return allGroups;
	}

}
