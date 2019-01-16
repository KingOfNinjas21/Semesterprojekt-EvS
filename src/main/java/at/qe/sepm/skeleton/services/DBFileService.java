package at.qe.sepm.skeleton.services;

import java.util.List;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.model.DBFile;
import at.qe.sepm.skeleton.repositories.DBFileRepository;

@Component
@Scope("application")
public class DBFileService
{
	@Autowired
	private DBFileRepository fileRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public DBFile loadDBFile(long id)
	{
		return fileRepository.findOne(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public List<DBFile> loadAll()
	{
		return fileRepository.findAll();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public DBFile saveDBFile(UploadedFile file)
	{
		String name = file.getFileName();
		String type = file.getContentType();
		byte[] bytes = file.getContents();
		return fileRepository.save(new DBFile(name, type, bytes));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteDBFile(UploadedFile file)
	{
		String name = file.getFileName();
		DBFile dbFile = fileRepository.findFirstByFileName(name);
		fileRepository.delete(dbFile);
	}

}
