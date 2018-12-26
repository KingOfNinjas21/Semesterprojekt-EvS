package at.qe.sepm.skeleton.model;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

@Entity
public class LabItem implements Persistable<Long> {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long itemId;

    @Column(nullable = false)
    private String itemName;
    
    @Column(nullable = false)
    private String labName;
    
    @Column(nullable = false)
    private String location; 
    
    private String description;
    
    // TODO: Time only for max 24 hours!
    @Column(nullable = false)
    private Time maxReservationTime;
    
    // TODO: List of Manuels
    private String manuel;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @ElementCollection(targetClass = ItemCondition.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "LabItem_Condition")
    @Enumerated(EnumType.STRING)
    private Set<ItemCondition> condition;
  
    
    public long getItemId() {
		return itemId;
	}


	public String getManuel() {
		return manuel;
	}


	public void setManuel(String manuel) {
		this.manuel = manuel;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getLabName() {
		return labName;
	}


	public void setLabName(String labName) {
		this.labName = labName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Time getMaxReservationTime() {
		return maxReservationTime;
	}


	public void setMaxReservationTime(Time maxReservationTime) {
		this.maxReservationTime = maxReservationTime;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Set<ItemCondition> getCondition() {
		return condition;
	}


	public void setCondition(Set<ItemCondition> condition) {
		this.condition = condition;
	}


	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LabItem)) {
            return false;
        }
        final LabItem other = (LabItem) obj;
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        return true;
    }
    
    
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	@Override
	public boolean isNew() {
		return (null == createDate);
	}


	@Override
	public String toString() {
		return itemName;
	}
}
