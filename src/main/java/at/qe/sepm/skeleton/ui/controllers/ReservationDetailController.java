package at.qe.sepm.skeleton.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.services.ReservationService;
import at.qe.sepm.skeleton.utils.CalendarView;
import at.qe.sepm.skeleton.utils.LabItemView;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Scope("view")
public class ReservationDetailController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private CalendarView calendarView;
	
	@Autowired
	private LabItemView labItemView;

	
    private Reservation reservation;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
   
    public void doReloadModel() {
    	reservation = reservationService.loadReservation(reservation.getReservedId());
    }

    public void doSaveModel() {

    	Reservation tmp = reservationService.loadReservation(reservation.getReservedId());
    	if (tmp.getIsReturned() != reservation.getIsReturned()) {
    		if (reservationService.isAdmin()) {
    			reservation = reservationService.save(reservation);
    		}
    	}
    	
    	//TODO: Benachrichtigung: Keine Berechtigung
    }


    public void doDeleteModel() {
    	// TODO: mittels AuditLogService loggen
        reservationService.remove(reservation);
        reservation = null;
    }
    
    public void doAddModel() {    	
    	Reservation entity = new Reservation();    	
    	Date begin = calendarView.getBeginDate();
    	Date end = calendarView.getEndDate();
    	
    	List<LabItem> items = labItemView.getSelectedLabItems();
    	
    	
    	if (items == null) {
    		//TODO: return message
    	}
    	
    	if (begin == null) {
    		//TODO: return message
    	}
    	
    	if (end == null) {
    		//TODO: return message
    	}
    	
    	if (begin.after(end)) {
    		//TODO: return message
    	}
    	
    	// TODO: begin und end ist innerhalb der Öffnungszeiten
    	
    	// TODO: Max. Reservierungsdauer nicht überschritten
    	
    	// TODO: Zustand überprüfen und richtig setzen
    	
    	for (LabItem item : items) {
    		log.debug("Saving: " + item);
    		
    		entity.setLabItem(item);
    		
    		entity.setReservationDate(begin);
    		entity.setReturnableDate(end);
    		entity.setCreateDate(new Date());
    		entity.setIsReturned(false);
    		
    		reservationService.save(entity);
    	}
	
    	calendarView.setBeginDate(null);
    	calendarView.setEndDate(null);
    	labItemView.setSelectedLabItems(null);
    }
    
    
    public boolean getRemoveDisabled() {
    	
    	if (reservationService.isStudent()) {
    		Date begin = reservation.getReservationDate();    		
    		if (begin.after(new Date())) {
    			return false;
    		}
    	} else if (reservationService.isAdmin()) {
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean getEditDisabled() {
		if (reservationService.isAdmin()) {
    		return false;
    	}
    	
    	return true;
    }
    
    
    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        doReloadModel();
    }

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

}
