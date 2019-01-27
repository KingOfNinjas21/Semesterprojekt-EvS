package at.qe.sepm.skeleton.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Entity to a create Group of Items
 * @author fbn
 */

@Entity
public class ItemGroup implements Persistable<Long> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2019364544560844557L;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="ITEM_GROUPS",
		joinColumns=@JoinColumn(name="GROUPID", referencedColumnName="GROUPID"),
		inverseJoinColumns=@JoinColumn(name="ITEMID", referencedColumnName="STOCKITEMID"))
    private Set<StockItem> items;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GroupId;

    @Column(nullable = false, unique = true)
    private String groupName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date groupCreationDate;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "User_username", nullable = false)
	private User user;
	
    public Set<StockItem> getItems() {
        return items;
    }

    public void setItems(Set<StockItem> arrayList) {
        this.items = arrayList;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public Date getGroupCreationDate() {
        return groupCreationDate;
    }

    public void setGroupCreationDate(Date groupCreationDate) {
        this.groupCreationDate = groupCreationDate;
    }

    @Override
    public Long getId() {
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
    public boolean isNew() {
        return (GroupId == null);
    }

    @Override
    public String toString() {
        return groupName;
    }
}

