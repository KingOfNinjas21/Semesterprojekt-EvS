package at.qe.sepm.skeleton.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity to a create Group of Items
 * @author fbn
 */

@Entity
public class LabItemGroup implements Persistable<Long> {
    @ManyToMany
    private LabItem labitem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GroupId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date groupCreationDate;

    public LabItem getLabitem() {
        return labitem;
    }

    public void setLabitem(LabItem labitem) {
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

    @Override
    public boolean isNew() {
        return false;
    }
}

