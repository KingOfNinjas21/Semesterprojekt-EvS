package at.qe.sepm.skeleton.ui.controllers;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.services.DBFileService;

@Component
public class FileController
{

	@Autowired
	private DBFileService dbFileService;

	private UploadedFile file;

	/**
	 * @return the file
	 */
	public UploadedFile getFile()
	{
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(UploadedFile file)
	{
		this.file = file;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void doSaveManual()
	{
		System.out.println("doSave");
		if (file == null)
		{
			System.out.println("do Save null");
			return;
		}
		this.dbFileService.saveDBFile(file);
	}

	/**
	 * Action to delete the currently displayed user.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	public void doDeleteStockItem()
	{
		this.dbFileService.deleteDBFile(file);
	}

	public void handleFileUpload(FileUploadEvent event)
	{
		System.out.println("handler");
		file = event.getFile();
		if (file == null)
		{
			System.err.println("handler Con null");
		}
		this.dbFileService.saveDBFile(file);

	}

}
