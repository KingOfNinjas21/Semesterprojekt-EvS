package at.qe.sepm.skeleton.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
public class CalendarView {
         
    private Date beginDate;
    private Date endDate;
     
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
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
