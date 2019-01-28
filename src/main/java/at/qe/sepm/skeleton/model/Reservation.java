package at.qe.sepm.skeleton.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

/**
 * Entity representing reservations.
 */
@Entity
public class Reservation implements Persistable<Long>
{

	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue
	private long reservedId;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade =
		{ CascadeType.ALL })
	private StockItem item;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationDate;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnableDate;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(nullable = false)
	private String reason;

	private boolean notified;

	private boolean isReturned;

	// TODO: Bug: Wird der User gelöscht und der aber eine Reservation erstellt hat,
	// kann der User nicht gelöscht werden!
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "User_username", nullable = false)
	private User user;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isNotified() {
		return notified;
	}

	public void setNotified(boolean notified) {
		this.notified = notified;
	}

	public long getReservedId()
	{
		return reservedId;
	}

	public void setReservedId(long reservedId)
	{
		this.reservedId = reservedId;
	}

	public StockItem getItem()
	{
		return item;
	}

	public void setItem(StockItem labItem)
	{
		this.item = labItem;
	}

	public Date getReservationDate()
	{
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate)
	{
		this.reservationDate = reservationDate;
	}

	public Date getReturnableDate()
	{
		return returnableDate;
	}

	public void setReturnableDate(Date returnableDate)
	{
		this.returnableDate = returnableDate;
	}

	public boolean getIsReturned()
	{
		return isReturned;
	}

	public void setIsReturned(boolean isReturned)
	{
		this.isReturned = isReturned;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public User getUser()
	{
		return this.user;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Reservation))
		{
			return false;
		}
		final Reservation other = (Reservation) obj;
		if (!Objects.equals(this.reservedId, other.reservedId))
		{
			return false;
		}
		return true;
	}

	@Override
	public Long getId()
	{
		return reservedId;
	}

	@Override
	public boolean isNew()
	{
		return (null == createDate);
	}

}
