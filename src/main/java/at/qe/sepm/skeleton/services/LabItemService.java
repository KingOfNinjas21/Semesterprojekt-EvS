package at.qe.sepm.skeleton.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;

@Component
@Scope("application")
public class LabItemService
{

	@Autowired
	LabItemRepository labItemRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public LabItem loadLabItem(long id)
	{
		return labItemRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public List<LabItem> loadAll()
	{
		return labItemRepository.findAll();
	}

	public LabItem loadByName(String name)
	{
		return labItemRepository.findFirstByItemName(name);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public LabItem saveLabItem(LabItem item)
	{
		if (item.isNew())
		{
			item.setCreateDate(new Date());
		}
		return labItemRepository.save(item);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteLabItem(LabItem item)
	{

		labItemRepository.delete(item);
	}

}
