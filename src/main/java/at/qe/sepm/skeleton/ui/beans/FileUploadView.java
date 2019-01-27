package at.qe.sepm.skeleton.ui.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import at.qe.sepm.skeleton.services.ManualService;

@ManagedBean
@Scope("view")
public class FileUploadView implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2153043266835468976L;
	private UploadedFile file;
	private Manual manual;
	private LabItem labItem;

	private List<UploadedFile> uploadedFiles;

	@Autowired
	private ManualService manualService;

	@PostConstruct
	private void init()
	{
		manual = new Manual();
		labItem = new LabItem();

		uploadedFiles = new ArrayList<UploadedFile>();

	}

	public UploadedFile getFile()
	{
		return file;
	}

	public void setFile(UploadedFile file)
	{
		this.file = file;
	}

	public void upload(LabItem labItem)
	{
		for (UploadedFile f : uploadedFiles)
		{
			if (f != null)
			{
				manual.setManualName(f.getFileName());

				manual.setType(f.getContentType());

				manual.setData(f.getContents());

				manual.setLabItem(labItem);

				manualService.saveManual(manual);

			}
		}
		uploadedFiles.clear();

	}

	public void handleFileUpload(FileUploadEvent event)
	{
		file = event.getFile();
		if (file != null)
		{

			FacesMessage message = new FacesMessage("Successful ");
			FacesContext.getCurrentInstance().addMessage(null, message);

			uploadedFiles.add(file);
		}

	}

	public void handleFileUploadEdit(FileUploadEvent event)
	{
		file = event.getFile();
		if (file != null)
		{
			FacesMessage message = new FacesMessage("Successful ");
			FacesContext.getCurrentInstance().addMessage(null, message);

			manual.setManualName(file.getFileName());

			manual.setType(file.getContentType());

			manual.setData(file.getContents());

			manual.setLabItem(labItem);

			manualService.saveManual(manual);

		}
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

	public void clearPuffer()
	{
		uploadedFiles.clear();
	}
}