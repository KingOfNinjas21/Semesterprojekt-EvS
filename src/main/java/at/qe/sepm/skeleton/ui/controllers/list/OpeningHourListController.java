package at.qe.sepm.skeleton.ui.controllers.list;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.OpeningHour;
import at.qe.sepm.skeleton.services.OpeningHourService;

@Component
@Scope("view")
public class OpeningHourListController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 483347618515172541L;
	@Autowired
	private OpeningHourService openingHourService;

	public Collection<OpeningHour> getAllOpeningHours()
	{
		return openingHourService.loadAll();
	}
}
