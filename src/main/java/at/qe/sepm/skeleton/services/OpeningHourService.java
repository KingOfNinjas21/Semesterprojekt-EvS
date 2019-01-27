package at.qe.sepm.skeleton.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.model.OpeningHour;
import at.qe.sepm.skeleton.repositories.OpeningHourRepository;

@Service
@Scope("application")
public class OpeningHourService
{

	@Autowired
	private OpeningHourRepository openingHourRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public OpeningHour loadOpeningHour(long id)
	{
		return openingHourRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or hasAuthority('STUDENT')")
	public List<OpeningHour> loadAll()
	{
		return openingHourRepository.findAll();
	}

	public OpeningHour loadByName(String name)
	{
		return openingHourRepository.findFirstByWeekday(name);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public OpeningHour saveOpeningHour(OpeningHour OpeningHour)
	{
		return openingHourRepository.save(OpeningHour);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void saveMultipleOpeningHours(OpeningHour OpeningHour, int count)
	{
		for (int i = 0; i < count; i++)
		{
			openingHourRepository.save(OpeningHour);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteOpeningHour(OpeningHour OpeningHour)
	{

		openingHourRepository.delete(OpeningHour);
	}


	public boolean withinOpeningHours(Date date) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int id = cal.get(Calendar.DAY_OF_WEEK);

		OpeningHour openingHour = this.loadOpeningHour(id);

		if (openingHour == null)
			return false;

		if (id == 7 || id == 1) // Samstag(7) u. Sonntag(1) clsoed
			return false;
		try
		{
			DateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			String time = simpleDateFormat.format(date);
			date = simpleDateFormat.parse(time);

		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		if (openingHour.getBreakStart() == null || openingHour.getBreakEnd() == null)
		{
			if (openingHour.getOpeningHour().after(date) || openingHour.getClosingHour().before(date))
				return false;
		} else
		{
			if (openingHour.getOpeningHour().after(date) || openingHour.getClosingHour().before(date))
				return false;

			if (!openingHour.getBreakStart().after(date) && !openingHour.getBreakEnd().before(date))
				return false;

		}
		return true;

	}

}
