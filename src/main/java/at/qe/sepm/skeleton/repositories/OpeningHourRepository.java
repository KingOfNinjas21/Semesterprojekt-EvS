package at.qe.sepm.skeleton.repositories;

import at.qe.sepm.skeleton.model.OpeningHour;

public interface OpeningHourRepository extends AbstractRepository<OpeningHour, Long>
{
	public OpeningHour findFirstByWeekday(String name);

}
