package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.ReservationService;
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
public class ReservationContoller {
	@Autowired
	private ReservationService reserveService;
	
	
    /**
     * Returns a list of all reservations.
     *
     * @return
     */
	public Collection<Reservation> getReservations() {    	
		return reserveService.getAllReservations();
	}
	
	public Collection<Reservation> getReservationsByUser()
	{
		return reserveService.getReservationsByUser();
	}

}
