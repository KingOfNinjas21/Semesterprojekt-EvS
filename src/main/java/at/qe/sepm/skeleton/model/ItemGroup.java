package at.qe.sepm.skeleton.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.domain.Persistable;

/**
 * Entity to a create Group of Items
 * 
 * @author fbn
 */

@Entity
public class ItemGroup implements Persistable<Long>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2019364544560844557L;

	@ManyToMany(fetch = FetchType.EAGER, cascade =
	{ CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ITEM_GROUPS", joinColumns = @JoinColumn(name = "GROUPID", referencedColumnName = "GROUPID"), inverseJoinColumns = @JoinColumn(name = "ITEMID", referencedColumnName = "STOCKITEMID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<StockItem> items;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long GroupId;

	@Column(nullable = false, unique = true)
	private String groupName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date groupCreationDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "User_username", nullable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private User user;

	public Set<StockItem> getItems()
	{
		return items;
	}

	public void setItems(Set<StockItem> arrayList)
	{
		this.items = arrayList;
	}

	public Long getGroupId()
	{
		return GroupId;
	}

	public void setGroupId(Long groupId)
	{
		GroupId = groupId;
	}

	public Date getGroupCreationDate()
	{
		return groupCreationDate;
	}

	public void setGroupCreationDate(Date groupCreationDate)
	{
		this.groupCreationDate = groupCreationDate;
	}

	@Override
	public Long getId()
	{
		return this.GroupId;
	}

	public String getGroupName()
	{
		return this.groupName;
	}

	public void setGroupName(String name)
	{
		this.groupName = name;
	}

	public User getUser()
	{
		return this.user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public boolean isNew()
	{
		return (GroupId == null);
	}

	@Override
	public String toString()
	{
		return groupName;
	}
	
	
	@Override
	public int hashCode()
	{
		return (getId() != null) ? (getClass().getSimpleName().hashCode() + getId().hashCode()) : super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof ItemGroup))
		{
			return false;
		}
		final ItemGroup other = (ItemGroup) obj;
		if (!Objects.equals(getId(), other.getId()))
		{
			return false;
		}
		return true;
	}
}
