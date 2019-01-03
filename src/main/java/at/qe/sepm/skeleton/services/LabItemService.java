package at.qe.sepm.skeleton.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.repositories.LabItemRepository;

/**
 * Service for accessing and manipulating for lab items.
 * @author Candir Salih
 */

//TODO: ggf. Berechtigungen für hinzufügen/überprüfen
@Component
@Scope("application")
public class LabItemService {

	
	@Autowired
	LabItemRepository labItemRepository;
	
	
    /**
     * Saves the item. This method will also set {@link LabItem#createDate} for new item.
     *
     * @param Entity to save
     * @return the updated Entity
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public LabItem save(LabItem entity) {
    	// TODO: add save function
    	return null;
    }
	
    /**
     * Deletes the Entity.
     *
     * @param Entity to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(LabItem entity) {
    	// TODO: Log with AuditLogService
    	// TODO: add remove function
    }
	
    /**
     * Returns a collection of all labItems.
     *
     * @return
     */
    public List<LabItem> loadAll() {
        return labItemRepository.findAll();
    }

    /**
     * Loads a single item identified by its id.
     *
     * @param labItem to search for
     * @return the labItem with the given id
     */
    public LabItem loadLabItem(long id) {
        return labItemRepository.findOne(id);
    }
    
    /**
     * Loads a single item identified by its name.
     *
     * @param labItem to search for
     * @return the labItem with the given name
     */
    public LabItem loadByName(String name) {
    	return labItemRepository.findFirstByItemName(name);
    }
    
}
