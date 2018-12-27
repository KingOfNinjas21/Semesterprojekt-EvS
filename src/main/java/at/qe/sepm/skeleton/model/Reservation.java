package at.qe.sepm.skeleton.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;



@Entity
public class Reservation implements Persistable<Long> {
	
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue
	private long reservedId;
	
	@ManyToOne(optional=false) 
	private LabItem labItem;

	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date returnableDate;
	
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

	private boolean isReturned;


	public long getReservedId() {
		return reservedId;
	}


	public void setReservedId(long reservedId) {
		this.reservedId = reservedId;
	}


	public LabItem getLabItem() {
		return labItem;
	}


	public void setLabItem(LabItem labItem) {
		this.labItem = labItem;
	}


	public Date getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}


	public Date getReturnableDate() {
		return returnableDate;
	}


	public void setReturnableDate(Date returnableDate) {
		this.returnableDate = returnableDate;
	}


	public boolean getIsReturned() {
		return isReturned;
	}


	public void setIsReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	
	
    public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Reservation)) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.reservedId, other.reservedId)) {
            return false;
        }
        return true;
    }


    @Override
    public Long getId() {
        return reservedId;
    }

    @Override
    public boolean isNew() {
        return (null == createDate);
    }

}
