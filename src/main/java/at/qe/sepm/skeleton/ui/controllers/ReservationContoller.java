package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.ReserveService;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("view") 
public class ReservationContoller {
	@Autowired
	private ReserveService reserveService;
	
	
	//reservations
	public Collection<Reservation> getReservations() {    	
		return reserveService.getAllReservations();
	}

}
