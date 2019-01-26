package at.qe.sepm.skeleton.utils;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class ManualView implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4082554770253983603L;
	private boolean show;

	@PostConstruct
	private void init()
	{
		show = false;
	}

	public boolean display()
	{
		if (show == true)
			show = false;
		else
			show = true;

		return show;
	}

	/**
	 * @return the show
	 */
	public boolean isShow()
	{
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(boolean show)
	{
		this.show = show;

	}

	public void reset()
	{

		show = false;
	}

}
