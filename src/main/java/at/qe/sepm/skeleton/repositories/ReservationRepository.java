package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.Reservation;

public interface ReservationRepository extends AbstractRepository<Reservation, Long> {

	public Reservation findFirstByReservedId(long id);

	
}
