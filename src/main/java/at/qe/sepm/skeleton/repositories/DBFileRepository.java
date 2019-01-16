package at.qe.sepm.skeleton.repositories;

import org.springframework.stereotype.Repository;

import at.qe.sepm.skeleton.model.DBFile;

@Repository
public interface DBFileRepository extends AbstractRepository<DBFile, Long>
{
	public DBFile findFirstByFileName(String name);

}
