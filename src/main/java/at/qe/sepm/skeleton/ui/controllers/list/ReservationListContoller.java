package at.qe.sepm.skeleton.ui.controllers.list;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.ReservationService;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the reservation list view.
 *
 * @author Candir Salih
 */
@Component
@Scope("view") 
public class ReservationListContoller implements Serializable {

	private static final long serialVersionUID = 3003919303087625754L;
	
	@Autowired
	private ReservationService reserveService;
	
	
    /**
     * Returns a list of all reservations.
     *
     * @return
     */
	public Collection<Reservation> getReservations() {    	
		return reserveService.loadAll();
	}

}
