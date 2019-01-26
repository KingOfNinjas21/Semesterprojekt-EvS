package at.qe.sepm.skeleton.model;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.Period;
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

	// @Column(nullable = false)
	// @Type(type="org.joda.time.contrib.hibernate.PersistentPeriod")
	// private Period maxReservationTime;

	@Column(nullable = true)
	private int days;
	@Column(nullable = true)
	private int hours;
	@Column(nullable = true)
	private int minutes;

	// TODO: List of Manuels
	private String manuel;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@OneToMany(mappedBy = "labItem")
	private List<StockItem> stockItems;

	@OneToMany(mappedBy = "labItem1")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Manual> manuals;

	/**
	 * @return the manuals
	 */
	public List<Manual> getManuals()
	{
		return manuals;
	}

	/**
	 * @param manuals
	 *            the manuals to set
	 */
	public void setManuals(List<Manual> manuals)
	{
		this.manuals = manuals;
	}

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

	public Period getMaxReservationTime()
	{
		if (days + hours + minutes <= 0)
			return null;

		Period p = new Period().withDays(days).withHours(hours).withMinutes(minutes);
		return p;
		// return maxReservationTime;
	}

	public String getMaxReservationTimeStr()
	{
		return String.format("Tage: %d Stunden: %d Minuten: %d", days, hours, minutes);
	}

	public void setMaxReservationTime(Period maxReservationTime)
	{
		this.days = maxReservationTime.getDays();
		this.hours = maxReservationTime.getHours();
		this.minutes = maxReservationTime.getMinutes();
		// this.maxReservationTime = maxReservationTime;
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

	/**
	 * @return the days
	 */
	public int getDays()
	{
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(int days)
	{
		this.days = days;
	}

	/**
	 * @return the hours
	 */
	public int getHours()
	{
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours)
	{
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes()
	{
		return minutes;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	/**
	 * @return the stockItems
	 */
	public List<StockItem> getStockItems()
	{
		return stockItems;
	}

	/**
	 * @param stockItems
	 *            the stockItems to set
	 */
	public void setStockItems(List<StockItem> stockItems)
	{
		this.stockItems = stockItems;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(long itemId)
	{
		this.itemId = itemId;
	}

}
