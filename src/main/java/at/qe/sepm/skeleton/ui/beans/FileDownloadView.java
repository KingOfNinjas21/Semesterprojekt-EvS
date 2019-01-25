package at.qe.sepm.skeleton.ui.beans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import at.qe.sepm.skeleton.model.Manual;
import at.qe.sepm.skeleton.services.ManualService;

@ManagedBean
@Scope("request")
public class FileDownloadView
{

	private StreamedContent file;
	private Manual manual;

	@Autowired
	private ManualService m;

	@PostConstruct
	private void init()
	{
		manual = new Manual();
	}

	public StreamedContent fileDownload()
	{
		manual = m.loadManual(1);
		InputStream stream = new ByteArrayInputStream(manual.getData());
		file = new DefaultStreamedContent(stream, manual.getType(), manual.getManualName());
		return file;

	}

	public StreamedContent getFile()
	{
		return file;
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
	 * @param file
	 *            the file to set
	 */
	public void setFile(StreamedContent file)
	{
		this.file = file;
	}

}