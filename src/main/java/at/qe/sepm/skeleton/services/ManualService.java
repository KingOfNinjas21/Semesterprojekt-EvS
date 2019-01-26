package at.qe.sepm.skeleton.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Manual;
import at.qe.sepm.skeleton.repositories.ManualRepository;

/**
 * Service for accessing and manipulating for lab items.
 * 
 * @author Candir Salih
 */

// TODO: ggf. Berechtigungen f�r hinzuf�gen/�berpr�fen
@Component
@Scope("application")
public class ManualService
{

	@Autowired
	ManualRepository manualRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public Manual loadManual(long id)
	{
		return manualRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Manual> loadAll()
	{
		return manualRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Manual saveManual(Manual manual)
	{
		return manualRepository.save(manual);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Manual reSaveManual(Manual item)
	{
		if (item.isNew())
		{
			item.setCreateDate(new Date());
		}

		return manualRepository.save(item);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteManual(Manual item)
	{

		manualRepository.delete(item);
	}

}
