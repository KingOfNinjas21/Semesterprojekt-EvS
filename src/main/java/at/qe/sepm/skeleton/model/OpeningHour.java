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
public class OpeningHour implements Persistable<Long>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String weekday;

	@Temporal(TemporalType.TIME)
	private Date openingHour;

	@Temporal(TemporalType.TIME)
	private Date closingHour;

	@Temporal(TemporalType.TIME)
	private Date breakStart;

	@Temporal(TemporalType.TIME)
	private Date breakEnd;

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the weekday
	 */
	public String getWeekday()
	{
		return weekday;
	}

	/**
	 * @param weekday
	 *            the weekday to set
	 */
	public void setWeekday(String weekday)
	{
		this.weekday = weekday;
	}

	/**
	 * @return the openingHour
	 */
	public Date getOpeningHour()
	{
		return openingHour;
	}

	/**
	 * @param openingHour
	 *            the openingHour to set
	 */
	public void setOpeningHour(Date openingHour)
	{
		this.openingHour = openingHour;
	}

	/**
	 * @return the closingHour
	 */
	public Date getClosingHour()
	{
		return closingHour;
	}

	/**
	 * @param closingHour
	 *            the closingHour to set
	 */
	public void setClosingHour(Date closingHour)
	{
		this.closingHour = closingHour;
	}

	/**
	 * @return the breakStart
	 */
	public Date getBreakStart()
	{
		return breakStart;
	}

	/**
	 * @param breakStart
	 *            the breakStart to set
	 */
	public void setBreakStart(Date breakStart)
	{
		this.breakStart = breakStart;
	}

	/**
	 * @return the breakEnd
	 */
	public Date getBreakEnd()
	{
		return breakEnd;
	}

	/**
	 * @param breakEnd
	 *            the breakEnd to set
	 */
	public void setBreakEnd(Date breakEnd)
	{
		this.breakEnd = breakEnd;
	}

	@Override
	public Long getId()
	{
		return id;
	}

	@Override
	public boolean isNew()
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breakEnd == null) ? 0 : breakEnd.hashCode());
		result = prime * result + ((breakStart == null) ? 0 : breakStart.hashCode());
		result = prime * result + ((closingHour == null) ? 0 : closingHour.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((openingHour == null) ? 0 : openingHour.hashCode());
		result = prime * result + ((weekday == null) ? 0 : weekday.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpeningHour other = (OpeningHour) obj;
		if (breakEnd == null)
		{
			if (other.breakEnd != null)
				return false;
		} else if (!breakEnd.equals(other.breakEnd))
			return false;
		if (breakStart == null)
		{
			if (other.breakStart != null)
				return false;
		} else if (!breakStart.equals(other.breakStart))
			return false;
		if (closingHour == null)
		{
			if (other.closingHour != null)
				return false;
		} else if (!closingHour.equals(other.closingHour))
			return false;
		if (id != other.id)
			return false;
		if (openingHour == null)
		{
			if (other.openingHour != null)
				return false;
		} else if (!openingHour.equals(other.openingHour))
			return false;
		if (weekday == null)
		{
			if (other.weekday != null)
				return false;
		} else if (!weekday.equals(other.weekday))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OpeningHour [id=" + id + ", weekday=" + weekday + "]";
	}

}
