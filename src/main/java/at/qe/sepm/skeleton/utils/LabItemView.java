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
	private List<LabItem> selectedItem;

	@PostConstruct
	public void init()
	{
		items = new ArrayList<LabItem>();
		selectedItem = new ArrayList<LabItem>();

		items.addAll(labItemRepository.findAll());
	}

	/**
	 * @return the items
	 */
	public List<LabItem> getItems()
	{
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<LabItem> items)
	{
		this.items = items;
	}

	/**
	 * @return the selectedItem
	 */
	public List<LabItem> getSelectedItem()
	{
		return selectedItem;
	}

	/**
	 * @param selectedItem
	 *            the selectedItem to set
	 */
	public void setSelectedItem(List<LabItem> selectedItem)
	{
		this.selectedItem = selectedItem;
	}

}