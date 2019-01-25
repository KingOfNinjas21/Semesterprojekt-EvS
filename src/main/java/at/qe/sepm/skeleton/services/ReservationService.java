package at.qe.sepm.skeleton.services;

import java.util.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.repositories.ReservationRepository;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;


/**
 * Service for accessing and manipulating reservation data.
 * 
 * @author Candir Salih
 */

// TODO: Berechtigungen f�r Student und Employee hinzuf�gen/�berpr�fen
@Component
@Scope("application")
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private SessionInfoBean sessionInfo;
   
	   
    /**
     * Saves the reservation. This method will also set {@link Reservation#createDate} for new reservation.
     * The user requesting this operation will also be stored as {@link Reservation#createUser}
     *
     * @param reservation to save
     * @return the updated reservation
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Reservation save(Reservation reserved) {
        if (reserved.isNew()) {
            reserved.setCreateDate(new Date());
            reserved.setUser(sessionInfo.getCurrentUser());
        }
        
        return reservationRepository.save(reserved);
    }
    
    /**
     * Deletes the reservation.
     *
     * @param reservation to delete
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or hasAuthority('STUDENT')")
    public void remove(Reservation entity) {
    	
    	// Only when is Student
    	if (sessionInfo.isStudent()) {
	    	if (!entity.getReservationDate().after(new Date())) {
	    		// TODO: return message
	    		return;
	    	}
    	}    	  	
    	
    	// TODO: Log with AuditLogService
    	reservationRepository.delete(entity);
    }
    
    /**
     * Loads a single reservation identified by its id.
     *
     * @param id to search for
     * @return the reservation with the given id
     */
    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public Reservation loadReservation(long id) {
        return reservationRepository.findFirstByReservedId(id);
    }
    
    /**
     * Returns a collection of all reservations.
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or hasAuthority('STUDENT')")
	public Collection<Reservation> getAllReservations() {

        if (!sessionInfo.isAdmin()) {
            return reservationRepository.findByUser(sessionInfo.getCurrentUser());
        }
		return reservationRepository.findAll();
	}
    

    

	
	
}
