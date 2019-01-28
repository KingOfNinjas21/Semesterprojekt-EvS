package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.repositories.ItemGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * LabItemGroupService to find the groups
 * @author fbn
 */

@Service
@Scope("application")
public class ItemGroupService {
	@Autowired
	private SessionInfoBean sessionInfo;
	
    @Autowired
    private ItemGroupRepository groupRepository;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public ItemGroup loadGroup(long id)
    {
        return groupRepository.findOne(id);
    }

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or hasAuthority('STUDENT')")
    public Collection<ItemGroup> getAllGroups()
    {
		if (!sessionInfo.isAdmin()) {
			if(sessionInfo.isStudent())
			{
				return new ArrayList<ItemGroup>();
			}
			return groupRepository.findByUser(sessionInfo.getCurrentUser());
		}

        return groupRepository.findAll();
    }
    
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
	public ItemGroup saveGroup(ItemGroup itemGroup)
	{
        if (itemGroup.isNew()) {
        	itemGroup.setUser(sessionInfo.getCurrentUser());
        	itemGroup.setGroupCreationDate(new Date());
        }
		return groupRepository.save(itemGroup);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteGroup(ItemGroup labItemGroup)
	{
		groupRepository.delete(labItemGroup);
	}

}
