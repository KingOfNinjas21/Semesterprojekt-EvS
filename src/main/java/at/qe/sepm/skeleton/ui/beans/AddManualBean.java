package at.qe.sepm.skeleton.ui.beans;

import javax.annotation.ManagedBean;

import org.primefaces.event.FileUploadEvent;

/**
 * Bean for add equipment manual functionality.
 */

@ManagedBean
public class AddManualBean
{

	public void handleFileUpload(FileUploadEvent event)
	{
		System.out.println("eban handler");
		if (event.getFile() == null)
			System.out.println("no file");

	}

}
