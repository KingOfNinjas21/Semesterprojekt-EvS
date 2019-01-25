package at.qe.sepm.skeleton.utils;


import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.StockItem;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.primefaces.model.LazyScheduleModel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@ManagedBean
@Scope("view")
@Controller
public class ScheduleView implements Serializable {
 
	private static final long serialVersionUID = 5658711789378769706L;

	private ScheduleModel eventModel;
     
    private ScheduleModel lazyEventModel;
    
    @Autowired
    private StockItemView stockItemView;
 
    private ScheduleEvent event = new DefaultScheduleEvent();
 
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        
        lazyEventModel = new LazyScheduleModel() {

			private static final long serialVersionUID = 3118556478308527818L;

			@Override
            public void loadEvents(Date start, Date end) {
				loadStockItemViewEvents();
            }   
        };
    }
    
    public void loadStockItemViewEvents()
    {	
    	for(StockItem stockitem : stockItemView.getSelectedItems())
    	{
    		for(Reservation reservation : stockitem.getReservations())
    		{
    			DefaultScheduleEvent e = new DefaultScheduleEvent(stockitem.getLabItem().getItemName(), reservation.getReservationDate(), reservation.getReturnableDate());
    			e.setDescription(e.getTitle() + "<br/>From: " + e.getStartDate().toString() + "<br/>To: " + e.getEndDate().toString());
    			
    			lazyEventModel.addEvent(e);
    		}
    	}
    }
     
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
         
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
 
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
   
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent() {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }

}
