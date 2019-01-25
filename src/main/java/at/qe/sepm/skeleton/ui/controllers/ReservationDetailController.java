package at.qe.sepm.skeleton.ui.controllers;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.StockItem;
import at.qe.sepm.skeleton.services.HolidayService;
import at.qe.sepm.skeleton.services.OpeningHourService;
import at.qe.sepm.skeleton.services.ReservationService;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;
import at.qe.sepm.skeleton.utils.CalendarView;
import at.qe.sepm.skeleton.utils.ErrorMessage;
import at.qe.sepm.skeleton.utils.StockItemView;

/**
 * Controller for the reservation detail view.
 *
 * @author Candir Salih
 */

@Component
@Scope("view")
public class ReservationDetailController
{

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
		reservation = reservationService.loadReservation(reservation.getReservedId());
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
		reservationService.remove(reservation);
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

		errorMessage.getFacesContext();
		
		if (holidayService.isHoliday(begin))
		{
			errorMessage.setMessage("Begin Date is holiday.");
			errorMessage.pushMessage();
		}

		if (holidayService.isHoliday(end))
		{
			errorMessage.setMessage("End Date is holiday.");
			errorMessage.pushMessage();
		}

		if (!openingHourService.withinOpeningHours(begin))
		{
			errorMessage.setMessage("Begin Date not within opening hours.");
			errorMessage.pushMessage();
		}

		if (!openingHourService.withinOpeningHours(end))
		{
			errorMessage.setMessage("End Date not within opening hours.");
			errorMessage.pushMessage();
		}

		if (items == null)
		{
			errorMessage.setMessage("Could not load items.");
			errorMessage.pushMessage();
		}

		if (begin == null)
		{
			errorMessage.setMessage("Invalid Begin Date.");
			errorMessage.pushMessage();
		}

		if (end == null)
		{
			errorMessage.setMessage("Invalid End Date.");
			errorMessage.pushMessage();
		}

		if (begin.after(end))
		{
			errorMessage.setMessage("Begin Date after End Date.");
			errorMessage.pushMessage();
		}
		
		if(errorMessage.hasError()) {
			return;
		}
			

		for (StockItem item : items)
		{
			if (!isAvailable(item, begin, end))
			{
				errorMessage.setMessage(String.format("Item: %s cannot be reserved at this time.",
													item.getLabItem().getItemName()));
				errorMessage.pushMessage();
				return;
			}
			
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

	public boolean isAvailable(StockItem item, Date from, Date to)
	{

		for (Reservation reservstion : item.getReservations())
		{

			if (reservstion.getIsReturned())
			{
				continue;
			}
			if (from.before(reservstion.getReservationDate()) && to.before(reservstion.getReservationDate()))
			{

				continue;
			}
			if (from.after(reservstion.getReturnableDate()) && to.after(reservstion.getReturnableDate()))
			{

				continue;
			}
			return false;
		}

		return true;
	}
}
