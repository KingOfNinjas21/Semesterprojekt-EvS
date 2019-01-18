package at.qe.sepm.skeleton.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.domain.Persistable;

@Entity
public class StockItem implements Persistable<Long>
{
	private static final long serialVersionUID = 6L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long stockItemId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "labItem_itemId", nullable = false)
	private LabItem labItem;

	private Date entryDate;

	@Column(nullable = false)
	private String labName;

	@Column(nullable = false)
	private String location;

	private String description;
	private boolean blocked;

	// @ElementCollection(targetClass = ItemCondition.class, fetch =
	// FetchType.EAGER)
	// @CollectionTable(name = "LabItem_Condition")
	// @Enumerated(EnumType.STRING)
	// private Set<ItemCondition> condition;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	private List<Reservation> reservations;

	/**
	 * @return the stockItemId
	 */
	public long getStockItemId()
	{
		return stockItemId;
	}

	/**
	 * @param stockItemId
	 *            the stockItemId to set
	 */
	public void setStockItemId(long stockItemId)
	{
		this.stockItemId = stockItemId;
	}

	/**
	 * @return the labItem
	 */
	public LabItem getLabItem()
	{
		return labItem;
	}

	/**
	 * @param labItem
	 *            the labItem to set
	 */
	public void setLabItem(LabItem labItem)
	{
		this.labItem = labItem;
	}

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * @return the labName
	 */
	public String getLabName()
	{
		return labName;
	}

	/**
	 * @param labName
	 *            the labName to set
	 */
	public void setLabName(String labName)
	{
		this.labName = labName;
	}

	/**
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the blocked
	 */
	public boolean isBlocked()
	{
		return blocked;
	}

	/**
	 * @param blocked
	 *            the blocked to set
	 */
	public void setBlocked(boolean blocked)
	{
		this.blocked = blocked;
	}

	/**
	 * @return the condition
	 */
	public Set<ItemCondition> getCondition()
	{
		Set<ItemCondition> condition = new HashSet<ItemCondition>();
		Date date = new Date();
		for (Reservation res : reservations)
		{
			if (res.getIsReturned())
				continue;

			if (res.getReturnableDate().before(date))
				condition.add(ItemCondition.OVERDUE);
			else
				condition.add(ItemCondition.RESERVED);

		}
		if (blocked == true)
			condition.add(ItemCondition.BLOCKED);
		if (condition.isEmpty())
			condition.add(ItemCondition.AVALIABLE);
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	// public void setCondition(Set<ItemCondition> condition)
	// {
	// this.condition = condition;
	//
	// }

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	/**
	 * @return the reservations
	 */
	public List<Reservation> getReservations()
	{
		return reservations;
	}

	/**
	 * @param reservations
	 *            the reservations to set
	 */
	public void setReservations(List<Reservation> reservations)
	{
		this.reservations = reservations;
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
		final StockItem other = (StockItem) obj;
		if (!Objects.equals(this.stockItemId, other.stockItemId))
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
		return labItem.getItemName();
	}

	@Override
	public Long getId()
	{
		return stockItemId;
	}

	@Override
	public boolean isNew()
	{
		return (labItem == null);
	}
}
