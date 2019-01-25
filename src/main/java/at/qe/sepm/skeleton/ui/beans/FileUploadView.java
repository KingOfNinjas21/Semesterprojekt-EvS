package at.qe.sepm.skeleton.ui.beans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import at.qe.sepm.skeleton.model.LabItem;
import at.qe.sepm.skeleton.model.Manual;
import at.qe.sepm.skeleton.repositories.ManualRepository;
import at.qe.sepm.skeleton.services.LabItemService;

@ManagedBean
@Scope("request")
public class FileUploadView
{

	private UploadedFile file;
	private Manual manual;
	private LabItem labItem;

	@Autowired
	private ManualRepository manualRepository;

	// for testing
	@Autowired
	private LabItemService lab;

	@PostConstruct
	private void init()
	{
		manual = new Manual();
		labItem = new LabItem();
	}

	public UploadedFile getFile()
	{
		return file;
	}

	public void setFile(UploadedFile file)
	{
		this.file = file;
	}

	public void upload()
	{
		if (file != null)
		{
			String name = file.getFileName();
			FacesMessage message = new FacesMessage("Succesful", name + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			manual.setManualName(name);

			manual.setType(file.getContentType());

			manual.setData(file.getContents());
			// manual.setLabItem(labItem);

			// for testing
			manual.setLabItem(lab.loadLabItem(2));
			manualRepository.save(manual);

		}
	}

	public void handleFileUpload(FileUploadEvent event)
	{
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	/**
	 * @return the labItem
	 */
	public LabItem getLabItem()
	{
		return labItem;
	}

	/**
	 * @param labItem
	 *            the labItem to set
	 */
	public void setLabItem(LabItem labItem)
	{
		this.labItem = labItem;
	}

}