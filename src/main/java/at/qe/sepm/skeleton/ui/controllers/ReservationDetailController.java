package at.qe.sepm.skeleton.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.ReserveService;

@Component
@Scope("view")
public class ReservationDetailController {
	
	@Autowired
	private ReserveService reserveService;
    private Reservation reservation;

    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        doReloadModel();
    }

    /**
     * Returns the currently displayed user.
     *
     * @return
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Action to force a reload of the currently displayed user.
     */
    public void doReloadModel() {
    	reservation = reserveService.loadReservation(reservation.getReservedId());
    }

    /**
     * Action to save the currently displayed user.
     */
    public void doSaveModel() {
    	reservation = reserveService.save(reservation);
    }

    /**
     * Action to delete the currently displayed user.
     */
    public void doDeleteModel() {
        reserveService.remove(reservation);
        reservation = null;
    }

}
