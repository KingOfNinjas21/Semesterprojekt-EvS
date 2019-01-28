package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.ItemGroup;
import at.qe.sepm.skeleton.repositories.ItemGroupRepository;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "itemGroupView")
@Scope("view")
@Controller
public class ItemGroupView implements Serializable 
{

	private static final long serialVersionUID = -6641200259516820244L;

	@Autowired
	private ItemGroupRepository groupRepository;

	private List<ItemGroup> itemGroups;
	private List<ItemGroup> selectedItemGroups;

	@PostConstruct
	public void init()
	{
		itemGroups = new ArrayList<ItemGroup>();
		selectedItemGroups = new ArrayList<ItemGroup>();

		itemGroups.addAll(groupRepository.findAll());
	}

	public List<ItemGroup> getItemGroups()
	{
		return itemGroups;
	}

	public List<ItemGroup> getSelectedItemGroups()
	{
		return selectedItemGroups;
	}

	public void setSelectedItemGroups(List<ItemGroup> selectedGroups)
	{
		this.selectedItemGroups = selectedGroups;
	}

}