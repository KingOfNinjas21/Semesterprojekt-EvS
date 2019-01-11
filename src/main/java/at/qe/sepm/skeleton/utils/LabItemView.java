package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "labItemView")
@Scope("view")
@Controller
public class LabItemView
{

	@Autowired
	private LabItemRepository labItemRepository;

	private List<LabItem> items;
	private List<LabItem> selectedItems;

	@PostConstruct
	public void init()
	{
		items = new ArrayList<LabItem>();
		selectedItems = new ArrayList<LabItem>();

		items.addAll(labItemRepository.findAll());
	}

	public List<LabItem> getItems()
	{
		return items;
	}

	public List<LabItem> getSelectedItems()
	{
		return selectedItems;
	}

	public void setSelectedItems(List<LabItem> selectedLabItems)
	{
		this.selectedItems = selectedLabItems;
	}

}