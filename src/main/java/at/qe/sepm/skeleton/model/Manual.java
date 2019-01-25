package at.qe.sepm.skeleton.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

@Entity
public class Manual implements Persistable<Long>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2368174158794695278L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String manualName;

	@Column(nullable = true)
	private String type;

	@Lob
	@Column(nullable = true)
	private byte[] data;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToOne(optional = false)
	private LabItem labItem1;

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the labItem1
	 */
	public LabItem getLabItem()
	{
		return labItem1;
	}

	/**
	 * @param labItem1
	 *            the labItem1 to set
	 */
	public void setLabItem(LabItem labItem1)
	{
		this.labItem1 = labItem1;
	}

	@Override
	public Long getId()
	{

		return id;
	}

	/**
	 * @return the manualName
	 */
	public String getManualName()
	{
		return manualName;
	}

	/**
	 * @param manualName
	 *            the manualName to set
	 */
	public void setManualName(String manualName)
	{
		this.manualName = manualName;
	}

	/**
	 * @return the data
	 */
	public byte[] getData()
	{
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data)
	{
		this.data = data;
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
	 * @return the createDate
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
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
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((labItem1 == null) ? 0 : labItem1.hashCode());
		result = prime * result + ((manualName == null) ? 0 : manualName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Manual other = (Manual) obj;
		if (createDate == null)
		{
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		if (id != other.id)
			return false;
		if (labItem1 == null)
		{
			if (other.labItem1 != null)
				return false;
		} else if (!labItem1.equals(other.labItem1))
			return false;
		if (manualName == null)
		{
			if (other.manualName != null)
				return false;
		} else if (!manualName.equals(other.manualName))
			return false;
		if (type == null)
		{
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "Manual [id=" + id + ", manualName=" + manualName + "]";
	}

}
