package at.qe.sepm.skeleton.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.model.Holiday;
import at.qe.sepm.skeleton.repositories.HolidayRepository;

@Service
@Scope("application")
public class HolidayService
{

	@Autowired
	private HolidayRepository holidayRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public Holiday loadHoliday(long id)
	{
		return holidayRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Holiday> loadAll()
	{
		return holidayRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Holiday saveHoliday(Holiday Holiday)
	{
		return holidayRepository.save(Holiday);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void saveMultipleHolidays(Holiday Holiday, int count)
	{
		for (int i = 0; i < count; i++)
		{
			holidayRepository.save(Holiday);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteHoliday(Holiday Holiday)
	{

		holidayRepository.delete(Holiday);
	}
	
	
	public boolean isHoliday(Date date) throws ParseException
	{
		try
		{
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
			String time = simpleDateFormat.format(date);
			date = simpleDateFormat.parse(time);

		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		for (Holiday holiday : holidayRepository.findAll())
		{
			if (date.equals(holiday.getDate()))
			{
				return true;
			}
		}
		return false;

	}

}
