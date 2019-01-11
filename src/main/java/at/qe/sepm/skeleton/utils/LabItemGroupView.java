package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.repositories.LabItemGroupRepository;

/**
 * A lab item view. Loads the items from {@code LabItemRepository}.
 * 
 * @author Candir Salih
 */
@ManagedBean(name = "labItemGroupView")
@Scope("view")
@Controller
public class LabItemGroupView
{

	@Autowired
	private LabItemGroupRepository labItemGroupRepository;

	private List<LabItemGroup> groups;
	private List<LabItemGroup> selectedGroups;

	@PostConstruct
	public void init()
	{
		groups = new ArrayList<LabItemGroup>();
		selectedGroups = new ArrayList<LabItemGroup>();

		groups.addAll(labItemGroupRepository.findAll());
	}

	public List<LabItemGroup> getGroups()
	{
		return groups;
	}

	public List<LabItemGroup> getSelectedGroups()
	{
		return selectedGroups;
	}

	public void setSelectedGroups(List<LabItemGroup> selectedLabItemGroups)
	{
		this.selectedGroups = selectedLabItemGroups;
	}

}