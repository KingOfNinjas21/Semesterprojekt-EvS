package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.User;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends AbstractRepository<Reservation, Long> {

	public Reservation findFirstByReservedId(long id);

	public Collection<Reservation> findByUser(User user);
	
	@Query("SELECT r FROM Reservation r WHERE r.isReturned = FALSE AND CAST(reservationDate AS date) <= CAST(GETDATE() AS date)")
	public Collection<Reservation> findActiveReservations();

	
}
