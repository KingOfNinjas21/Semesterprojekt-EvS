package at.qe.sepm.skeleton.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity to a create Group of Items
 * @author fbn
 */

@Entity
public class LabItemGroup implements Persistable<Long> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2019364544560844557L;

	@ManyToMany
	@JoinTable(
		name="ITEM_GROUPS",
		joinColumns=@JoinColumn(name="GROUP_ID", referencedColumnName="GROUPID"),
		inverseJoinColumns=@JoinColumn(name="ITEM_ID", referencedColumnName="ITEMID"))
    private List<LabItem> labitem;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GroupId;

    @Column(nullable = false, unique = true)
    private String groupName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date groupCreationDate;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "User_username", nullable = false)
	private User user;
	
    public List<LabItem> getLabitem() {
        return labitem;
    }

    public void setLabitem(List<LabItem> labitem) {
        this.labitem = labitem;
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
    
    public User getUser() 
    {
    	return this.user;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}

