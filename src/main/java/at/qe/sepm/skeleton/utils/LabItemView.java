package at.qe.sepm.skeleton.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;

@ManagedBean(name = "labItemView") 
@Controller
public class LabItemView {
	
	@Autowired
	LabItemRepository labItemRepository;
 
    private List<LabItem> labItems;
    private LabItem[] selectedLabItems;


	@PostConstruct
    public void init() {
		List<LabItem> itemList = labItemRepository.findAll();
		labItems = new ArrayList<>();
		
		// TODO: do better
		for( LabItem l : itemList )  {
			labItems.add(l);
		}
    }

	public List<LabItem> getLabItems() {
		return labItems;
	}
	
    public LabItem[] getSelectedLabItems() {
		return selectedLabItems;
	}

	public void setSelectedLabItems(LabItem[] selectedLabItems) {
		this.selectedLabItems = selectedLabItems;
	}
 

}