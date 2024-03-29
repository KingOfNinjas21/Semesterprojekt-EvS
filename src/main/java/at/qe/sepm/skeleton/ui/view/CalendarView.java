package at.qe.sepm.skeleton.ui.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * A calendar view to store two dates. 
 * 
 * @author Candir Salih
 */
@ManagedBean
@Controller
@Scope("view")
public class CalendarView implements Serializable {
    
	private static final long serialVersionUID = 2058548888863786507L;
	
	private Date beginDate;
    private Date endDate;
    private Date today;

    public Date getOpeningHour() {
        return openingHour;
    }

    public Date getClosingHour() {
        return closingHour;
    }

    private Date openingHour, closingHour;
    
    @PostConstruct
    public void init() {
		Calendar cal = Calendar.getInstance();
		setToday(cal.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		
    	beginDate = cal.getTime();
    	endDate = cal.getTime();
    	
    	openingHour = new Date();
    	closingHour = new Date();

    	openingHour.setTime(8*60*60*1000);
    	closingHour.setTime(17*60*60*1000);
    }
     
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
 
    public Date getBeginDate() {
        return beginDate;
    }
 
    public void setBeginDate(Date date) {
        this.beginDate = date;
        if(endDate.before(beginDate)){
            endDate = beginDate;
        }
    }
 
    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date date) {
        this.endDate = date;
    }
    
	public Date getToday() {
        today = Calendar.getInstance().getTime();
        return today;
    }

	public void setToday(Date today) {
		this.today = today;
	}
}
