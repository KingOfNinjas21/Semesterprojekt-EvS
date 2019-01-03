package at.qe.sepm.skeleton.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock
{
	private static final long serialVersionUID = 6L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int StockId;

	@Column(nullable = false)
	private String itemId;

	private Date entryDate;

	/**
	 * @return the stockId
	 */
	public int getStockId()
	{
		return StockId;
	}

	/**
	 * @param stockId
	 *            the stockId to set
	 */
	public void setStockId(int stockId)
	{
		StockId = stockId;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
