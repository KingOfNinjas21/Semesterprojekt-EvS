package at.qe.sepm.skeleton.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

@Entity
public class Holiday implements Persistable<Long>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3528182387756039708L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getBeginDate()
	{
		return beginDate;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setBeginDate(Date date)
	{
		this.beginDate = date;
	}

	@Override
	public Long getId()
	{

		return id;
	}

	@Override
	public boolean isNew()
	{
		return (beginDate == null);
	}

}
