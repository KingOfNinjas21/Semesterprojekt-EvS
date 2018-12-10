package at.qe.sepm.skeleton.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;



@Entity
public class Reserved implements Persistable<String> {
	
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservedId;
	
	@ManyToOne(optional=false) 
	private LabItem labItem;

	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date returnableDate;
	
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
	

	private boolean isReturned;


	public LabItem getItem() {
		return labItem;
	}


	public void setItem(LabItem item) {
		this.labItem = item;
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


	public boolean isReturned() {
		return isReturned;
	}


	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Reserved)) {
            return false;
        }
        final Reserved other = (Reserved) obj;
        if (!Objects.equals(this.reservedId, other.reservedId)) {
            return false;
        }
        return true;
    }


    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNew() {
        return (null == createDate);
    }

}
