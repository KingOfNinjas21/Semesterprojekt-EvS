package at.qe.sepm.skeleton.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

@Entity
public class AuditLog implements Persistable<Long> {
	
	private static final long serialVersionUID = 4L;
	
	@Id
	@GeneratedValue
	private long id;
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	@ManyToOne(optional=false)
	private User updateUser;
	
	
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String msg) {
		this.message = msg;
	}
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User user) {
		this.updateUser = user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public boolean isNew() {
		return (message == null);
	}

}
