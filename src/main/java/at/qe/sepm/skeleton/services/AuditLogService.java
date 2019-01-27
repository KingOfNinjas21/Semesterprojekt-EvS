package at.qe.sepm.skeleton.services;

import java.util.Date;

import at.qe.sepm.skeleton.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.model.AuditLog;
import at.qe.sepm.skeleton.model.User;
import at.qe.sepm.skeleton.repositories.AuditLogRepository;
import at.qe.sepm.skeleton.ui.beans.SessionInfoBean;


/**
 * Service for logging.
 * @author Candir Salih
 */
@Service
@Scope("application")
public class AuditLogService {
	
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	@Autowired
	@Lazy // Circular Dependencies
	private SessionInfoBean sessionInfo;
	
   
    /**
     * Log's a User delete.
     *
     * @param username the username to search for
     */
	public void userDeleted(String username) {
		AuditLog entity = new AuditLog();
		User user = new User();
		user = sessionInfo.getCurrentUser();
		
	    entity.setMessage(String.format("User: %s deleted", username));
	    entity.setTime(new Date());
	    entity.setUpdateUser(user);
	    
	    auditLogRepository.save(entity);
	}

	public void reservationCreatedEmailLog(Reservation reservation){
		AuditLog entity = new AuditLog();
		entity.setMessage(String.format("[EMAIL] User %s notified about reservation %s", reservation.getUser(), reservation.getItem()));
		entity.setTime(new Date());

		auditLogRepository.save(entity);
	}

}
