package at.qe.sepm.skeleton.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

/**
 * Entity representing lab items.
 */
@Entity
public class LabItem implements Persistable<Long>
{

	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;

	/**
	 * itemName needs to be unique, otherwise the {@code CustomConverter} won't be
	 * able to set the right entity back in the model.
	 * 
	 * @author Candir Salih
	 * @see {@code CustomConverter}
	 */
	@Column(nullable = false, unique = true)
	private String itemName;

	// TODO: Time only for max 24 hours!
	@Column(nullable = false)
	private Time maxReservationTime;

	// TODO: List of Manuels
	private String manuel;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@OneToMany(mappedBy = "labItem")
	private List<StockItem> stockItems;

	public long getItemId()
	{
		return itemId;
	}

	public String getManuel()
	{
		return manuel;
	}

	public void setManuel(String manuel)
	{
		this.manuel = manuel;
	}

	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public Time getMaxReservationTime()
	{
		return maxReservationTime;
	}

	public void setMaxReservationTime(Time maxReservationTime)
	{
		this.maxReservationTime = maxReservationTime;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof LabItem))
		{
			return false;
		}
		final LabItem other = (LabItem) obj;
		if (!Objects.equals(this.itemId, other.itemId))
		{
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		return (getId() != null) ? (getClass().getSimpleName().hashCode() + getId().hashCode()) : super.hashCode();
	}

	@Override
	public String toString()
	{
		return itemName;
	}

	@Override
	public Long getId()
	{
		return itemId;
	}

	@Override
	public boolean isNew()
	{
		return (null == createDate);
	}

}
