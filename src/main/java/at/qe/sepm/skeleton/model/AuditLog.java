package at.qe.sepm.skeleton.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.domain.Persistable;


/**
 * Entity representing logs.
 */
@Entity
public class AuditLog implements Persistable<Long> {
	
	private static final long serialVersionUID = 4L;
	
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String message;

	//Information
	private String deletedClass;
	
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
