package at.qe.sepm.skeleton.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;
import at.qe.sepm.skeleton.repositories.StockRepository;

/**
 * Service for accessing and manipulating for lab items.
 * 
 * @author Candir Salih
 */

@Service
@Scope("application")
public class LabItemService
{

	@Autowired
	LabItemRepository labItemRepository;

	@Autowired
	private StockItemService stockitemSeervice;

	@Autowired
	private StockRepository stockrepository;

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
		LabItem temp = item;
		String name = item.getItemName();
		if (item.isNew())
		{
			item.setCreateDate(new Date());
		}

		temp = labItemRepository.findFirstByItemName(name);

		if (temp == null)
			return labItemRepository.save(item);

		return temp;

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public LabItem reSaveLabItem(LabItem item)
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
		List<StockItem> stockitems = new ArrayList<>(stockrepository.findAllByLabItem(item));
		for (StockItem sItem : stockitems)
		{
			item.getStockItems().remove(sItem);
			stockitemSeervice.deleteStockItem(sItem);
			item = labItemRepository.save(item);

		}
		labItemRepository.delete(item);
	}

	public void deletAllStockItems(LabItem item)
	{

	}

}
