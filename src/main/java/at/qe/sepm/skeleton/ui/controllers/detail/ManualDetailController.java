package at.qe.sepm.skeleton.ui.controllers.detail;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.Manual;
import at.qe.sepm.skeleton.services.ManualService;

@Component
@Scope("view")
public class ManualDetailController implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6816261753263011123L;

	@Autowired
	private ManualService manualService;

	@Autowired
	private LabItemDetailController labItemController;

	private Manual manual;

	@PostConstruct
	private void init()
	{
		manual = new Manual();
	}

	public void doReloadManual()
	{
		manual = manualService.loadManual(manual.getId());
	}

	/**
	 * Action to save the currently displayed LabItemGroup.
	 */
	public void doSaveManual()
	{
		manual = this.manualService.saveManual(manual);
	}

	/**
	 * Action to delete the currently displayed LabItemGroup.
	 */
	public void doDeleteManual()
	{
		this.manualService.deleteManual(manual);
		labItemController.doReloadLabItem();
		manual = null;
	}

	/**
	 * @return the manual
	 */
	public Manual getManual()
	{
		return manual;
	}

	/**
	 * @param manual
	 *            the manual to set
	 */
	public void setManual(Manual manual)
	{
		this.manual = manual;
	}

}
