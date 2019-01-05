package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;

@ManagedBean(name = "labItemView")
@Scope("view")
@Controller
public class LabItemView
{

	@Autowired
	private LabItemRepository labItemRepository;

	private List<LabItem> labItems;
	private List<LabItem> selectedLabItems;

	@PostConstruct
	public void init()
	{
		labItems = new ArrayList<LabItem>();
		selectedLabItems = new ArrayList<LabItem>();

		labItems.addAll(labItemRepository.findAll());
	}

	public List<LabItem> getLabItems()
	{
		return labItems;
	}

	public Collection<LabItem> getAllLabItems()
	{
		return labItemRepository.findAll();
	}

	public List<LabItem> getSelectedLabItems()
	{
		return selectedLabItems;
	}

	public void setSelectedLabItems(List<LabItem> selectedLabItems)
	{
		this.selectedLabItems = selectedLabItems;
	}

}