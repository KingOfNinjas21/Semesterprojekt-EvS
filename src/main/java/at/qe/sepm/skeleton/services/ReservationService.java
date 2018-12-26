package at.qe.sepm.skeleton.services;

import java.util.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.repositories.ReservationResository;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;

@Component
@Scope("application")
public class ReservationService {
	
	@Autowired
	private ReservationResository reservationResository;
	
	@Autowired
	private SessionInfoBean sessionInfo;
	
	
    @PreAuthorize("hasAuthority('ADMIN')")
    public void add(Reservation res) {
       
    	reservationResository.save(res);
    }
    
    
    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public Reservation loadReservation(long id) {
        return reservationResository.findFirstByReservedId(id);
    }
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    public Reservation save(Reservation reserved) {
        return reservationResository.save(reserved);
    }
    
    
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or principal.username eq #username")
    public void remove(Reservation entity) {
    	
    	// Only when is Student
    	if (sessionInfo.getCurrentUserRoles().contains("STUDENT") ) {
	    	if (!entity.getReservationDate().after(new Date())) {
	    		return ;
	    	}
    	}    	  	
    	
    	reservationResository.delete(entity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
	public Collection<Reservation> getAllReservations() {		
		return reservationResository.findAll();
	}
    
    
	
	
}
