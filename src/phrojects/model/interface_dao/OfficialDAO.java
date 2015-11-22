package phrojects.model.interface_dao;

import java.sql.SQLException;

import phrojects.model.objects.Official;

public interface OfficialDAO {
	public Official[] getAllOfficials();
	public Official getOfficial(String official_id);
}   