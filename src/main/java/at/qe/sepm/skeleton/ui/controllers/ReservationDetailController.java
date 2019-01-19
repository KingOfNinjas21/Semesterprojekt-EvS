package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.ReservationService;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;
import at.qe.sepm.skeleton.utils.CalendarView;
import at.qe.sepm.skeleton.utils.StockItemView;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller for the reservation detail view.
 *
 * @author Candir Salih
 */
@Component
@Scope("view")
public class ReservationDetailController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private SessionInfoBean sessionInfo;
	
	@Autowired
	private CalendarView calendarView;
	
	@Autowired
	private StockItemView stockItemView;

	@Autowired
	private ErrorMessage errorMessage;
	
    private Reservation reservation;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	/**
     * Action to force a reload of the currently displayed reservation.
     */
    public void doReloadModel() {
    	reservation = reservationService.loadReservation(reservation.getReservedId());
    }

    /**
     * Action to save the currently displayed reservation.
     */
    public void doSaveModel() {

    	reservation = reservationService.save(reservation);
    }

    /**
     * Action to delete the currently displayed reservation.
     */
    public void doDeleteModel() {
        reservationService.remove(reservation);
        reservation = null;
    }
    
    
    /**
     * Action to add a new reservation.
     */
    public void doAddModel() {    	
    	Reservation entity = new Reservation();    	
    	Date begin = calendarView.getBeginDate();
    	Date end = calendarView.getEndDate();
    	
    	List<StockItem> items = stockItemView.getSelectedItems();
    	
    	
    	if (items == null) {
			errorMessage.setMessage("Items konnten nicht geladen werden!");
			return;
    	}
    	
    	if (begin == null) {
			errorMessage.setMessage("Ungültige Startzeit!");
			return;
    	}
    	
    	if (end == null) {
			errorMessage.setMessage("Ungültige Endzeit!");
			return;
    	}
    	
    	if (begin.after(end)) {
			errorMessage.setMessage("Startzeit nach Endzeit!");
			return;
    	}
    	
    	// TODO: begin und end ist innerhalb der �ffnungszeiten
    	
    	// TODO: Max. Reservierungsdauer nicht �berschritten
    	
    	// TODO: Zustand �berpr�fen und richtig setzen
    	
    	for (StockItem item : items) {
    		log.debug("Saving: " + item);
    		
    		entity.setItem(item);
    		
    		entity.setReservationDate(begin);
    		entity.setReturnableDate(end);
    		entity.setIsReturned(false);
    		
    		reservationService.save(entity);
    	}
	
    	calendarView.setBeginDate(null);
    	calendarView.setEndDate(null);
    	stockItemView.setSelectedItems(null);
    }
    
    /**
     * CommandButton is enabled when user is Admin or a Student with a reservation in the future, otherwise it's disabled.
     * 
     * @return
     */
    public boolean getRemoveDisabled() {
    	
    	if (sessionInfo.isStudent()) {
    		Date begin = reservation.getReservationDate();    		
    		if (begin.after(new Date())) {
    			return false;
    		}
    	} else if (sessionInfo.isAdmin()) {
    		return false;
    	}
    	
    	return true;
    }
    
    
    /**
     * CommandButton is enabled when user is Admin, otherwise it's disabled.
     * 
     * @return
     */
    public boolean getEditDisabled() {    	
    	return !sessionInfo.isAdmin();
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

	public StockItemView getStockItemView() {
		return stockItemView;
	}

	public void setStockItemView(StockItemView labItemView) {
		this.stockItemView = labItemView;
	}

}
