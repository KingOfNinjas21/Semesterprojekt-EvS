package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.HolidayService;
import at.qe.sepm.skeleton.services.OpeningHourService;
import at.qe.sepm.skeleton.services.ReservationService;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;
import at.qe.sepm.skeleton.ui.view.CalendarView;
import at.qe.sepm.skeleton.ui.view.StockItemView;
import at.qe.sepm.skeleton.utils.ErrorMessage;

/**
 * Controller for the reservation detail view.
 *
 * @author Candir Salih
 */

@Component
@Scope("view")
public class ReservationDetailController implements Serializable 
{

	private static final long serialVersionUID = -5879319392373733435L;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private SessionInfoBean sessionInfo;

	@Autowired
	private CalendarView calendarView;

	@Autowired
	private StockItemView stockItemView;

	@Autowired
	private OpeningHourService openingHourService;

	@Autowired
	private HolidayService holidayService;

	@Autowired
	private ErrorMessage errorMessage;

	private Reservation reservation;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Action to force a reload of the currently displayed reservation.
	 */
	public void doReloadModel()
	{
		reservation = reservationService.loadReservation(reservation);
	}

	/**
	 * Action to save the currently displayed reservation.
	 */
	public void doSaveModel()
	{

		reservation = reservationService.save(reservation);
	}

	/**
	 * Action to delete the currently displayed reservation.
	 */
	public void doDeleteModel()
	{
		try {
			reservationService.remove(reservation);
		} catch (AccessDeniedException e) {
			errorMessage.pushMessage(e.getMessage());
			return;
		}
		
		reservation = null;
	}

	/**
	 * Action to add a new reservation.
	 * 
	 * @throws ParseException
	 */

	public void doAddModel() throws ParseException
	{

		Reservation entity = new Reservation();
		Date begin = calendarView.getBeginDate();
		Date end = calendarView.getEndDate();

		List<StockItem> items = stockItemView.getSelectedItems();
		
		if (holidayService.isHoliday(begin))
		{
			errorMessage.pushMessage("Begin Date is holiday.");
		}

		if (holidayService.isHoliday(end))
		{
			errorMessage.pushMessage("End Date is holiday.");
		}

		if (!openingHourService.withinOpeningHours(begin))
		{
			errorMessage.pushMessage("Begin Date not within opening hours.");
		}

		if (!openingHourService.withinOpeningHours(end))
		{
			errorMessage.pushMessage("End Date not within opening hours.");
		}

		if (items == null)
		{
			errorMessage.pushMessage("Could not load items.");
		}

		if (begin == null)
		{
			errorMessage.pushMessage("Invalid Begin Date.");
		}

		if (end == null)
		{
			errorMessage.pushMessage("Invalid End Date.");
		}

		if (begin.after(end))
		{
			errorMessage.pushMessage("Begin Date after End Date.");
		}
		
		if(errorMessage.hasError()) {
			return;
		}
	
		for (StockItem item : items)
		{
			if (!isAvailable(item, begin, end))
			{
				return;
			}
		}

		for (StockItem item : items)
		{			
			log.debug("Saving: " + item);

			entity.setItem(item);
			entity.setReservationDate(begin);
			entity.setReturnableDate(end);
			entity.setIsReturned(false);
			
			reservation = reservationService.save(entity);
			item.addReservation(reservation);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		Date beginDate = cal.getTime();
		
		calendarView.setBeginDate(beginDate);
		calendarView.setEndDate(beginDate);
		stockItemView.setSelectedItems(null);
	}
	
	
	
	private boolean isAvailable(StockItem item, Date from, Date to)
	{
		DateTime dt = new DateTime(from);
		Period period = item.getLabItem().getMaxReservationTime();
		
		if (period == null) {
			throw new NullPointerException("getMaxReservationTime is null");
		}
		
		if (dt.plus(period).isBefore(to.getTime()) ) {
			errorMessage.pushMessage("Max reservation time exceeded");
			return false;
		}

		for (Reservation reservstion : item.getReservations()) {

			if (reservstion.getIsReturned()) {
				continue;
			}
			if (from.before(reservstion.getReservationDate()) && to.before(reservstion.getReservationDate())) {
				continue;
			}
			if (from.after(reservstion.getReturnableDate()) && to.after(reservstion.getReturnableDate())) {
				continue;
			}
			errorMessage.pushMessage(String.format("Item: %s cannot be reserved at this time.",
					item.getLabItem().getItemName()));
			return false;
		}

		return true;
	}

	/**
	 * CommandButton is enabled when user is Admin or a Student with a reservation
	 * in the future, otherwise it's disabled.
	 * 
	 * @return
	 */
	public boolean getRemoveDisabled()
	{
		if (reservation == null)
			return true;
 
		if (sessionInfo.isStudent())
		{
			Date begin = reservation.getReservationDate();
			if (begin.after(new Date()))
			{
				return false;
			}
		} else if (sessionInfo.isAdmin())
		{
			return false;
		}

		return true;
	}

	/**
	 * CommandButton is enabled when user is Admin, otherwise it's disabled.
	 * 
	 * @return
	 */
	public boolean getEditDisabled()
	{
		return !sessionInfo.isAdmin();
	}

	public void setReservation(Reservation reservation)
	{
		this.reservation = reservation;
		doReloadModel();
	}

	public Reservation getReservation()
	{
		return reservation;
	}

	public CalendarView getCalendarView()
	{
		return calendarView;
	}

	public void setCalendarView(CalendarView calendarView)
	{
		this.calendarView = calendarView;
	}

	public StockItemView getStockItemView()
	{
		return stockItemView;
	}

	public void setStockItemView(StockItemView labItemView)
	{
		this.stockItemView = labItemView;
	}
}
