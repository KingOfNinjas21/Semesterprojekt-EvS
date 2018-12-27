package at.qe.sepm.skeleton.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.LabItemService;
import at.qe.sepm.skeleton.services.ReservationService;
import at.qe.sepm.skeleton.utils.CalendarView;
import at.qe.sepm.skeleton.utils.LabItemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Scope("view")
public class ReservationDetailController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private LabItemService labItemService;
	
	@Autowired
	private CalendarView calendarView;
	
	@Autowired
	private LabItemView labItemView;
	
    private Reservation reservation;
    
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
   
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

	public CalendarView getCalendarView() {
		return calendarView;
	}

	public void setCalendarView(CalendarView calendarView) {
		this.calendarView = calendarView;
	}

	public LabItemView getLabItemView() {
		return labItemView;
	}

	public void setLabItemView(LabItemView labItemView) {
		this.labItemView = labItemView;
	}

	/**
     * Action to force a reload of the currently displayed user.
     */
    public void doReloadModel() {
    	reservation = reservationService.loadReservation(reservation.getReservedId());
    }

    /**
     * Action to save the currently displayed user.
     */
    public void doSaveModel() {
    	reservation = reservationService.save(reservation);
    }

    /**
     * Action to delete the currently displayed user.
     */
    public void doDeleteModel() {
        reservationService.remove(reservation);
        reservation = null;
    }
    
    public void doAddModel() {    	
    	Reservation entity = new Reservation();    	
    	
    	for (String item : labItemView.getSelectedLabItems()) {
    		log.debug("Saving: " + item);
    		
    		entity.setLabItem(labItemService.loadByName(item));
    		entity.setReservationDate(calendarView.getBeginDate());
    		entity.setReturnableDate(calendarView.getEndDate());
    		entity.setIsReturned(false);
    		
    		reservationService.save(entity);
    	}
    	
    	calendarView.setBeginDate(null);
    	calendarView.setEndDate(null);
    	labItemView.setSelectedLabItems(null);
    	entity = null;
    }

}
