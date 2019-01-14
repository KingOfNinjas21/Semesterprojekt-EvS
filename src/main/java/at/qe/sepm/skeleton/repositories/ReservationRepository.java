package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.LabItemGroup;
import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.User;

import java.util.Collection;

public interface ReservationRepository extends AbstractRepository<Reservation, Long> {

	public Reservation findFirstByReservedId(long id);

	public Collection<Reservation> findByUser(User user);

	
}
