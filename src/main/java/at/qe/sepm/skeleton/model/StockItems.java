package at.qe.sepm.skeleton.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class StockItems
{
	private static final long serialVersionUID = 6L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long stockItemId;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "labItem_itemId", nullable = false)
	private LabItem labItem;

	private Date entryDate;

	@Column(nullable = false)
	private String labName;

	@Column(nullable = false)
	private String location;

	private String description;

	@ElementCollection(targetClass = ItemCondition.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "LabItem_Condition")
	@Enumerated(EnumType.STRING)
	private Set<ItemCondition> condition;

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
	 * @return the condition
	 */
	public Set<ItemCondition> getCondition()
	{
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(Set<ItemCondition> condition)
	{
		this.condition = condition;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
