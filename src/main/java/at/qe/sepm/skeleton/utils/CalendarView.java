package at.qe.sepm.skeleton.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope("view")
public class CalendarView {
    
    private Date beginDate;
    private Date endDate;
    
    @PostConstruct
    public void init() {
    	beginDate = new Date();
    	endDate = new Date();
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
    }
 
    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date date) {
        this.endDate = date;
    }
}
