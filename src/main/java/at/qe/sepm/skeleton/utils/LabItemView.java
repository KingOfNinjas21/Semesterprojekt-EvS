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

@ManagedBean(name = "labItemView")
@Scope("view")
@Controller
public class LabItemView {
	
	@Autowired
	LabItemRepository labItemRepository;
 
	
	// TODO: implement a Converter and use List of LabItems
    private List<String> labItems;
    private String[] selectedLabItems;


	@PostConstruct
    public void init() {
		labItems = new ArrayList<>();
		List<LabItem> items = labItemRepository.findAll();
		
		for (LabItem item : items) {
			labItems.add(item.getItemName());
		}
    }

	public List<String> getLabItems() {
		return labItems;
	}
	
    public String[] getSelectedLabItems() {
		return selectedLabItems;
	}

	public void setSelectedLabItems(String[] selectedLabItems) {
		this.selectedLabItems = selectedLabItems;
	}
 

}