package phrojects.model.mysql_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import phrojects.model.MySQLConnectionManager;

import phrojects.model.interface_dao.OfficialDAO;
import phrojects.model.objects.Official;

public class MySQLOfficialDAO implements OfficialDAO {
	private Connection conn;
	
	public MySQLOfficialDAO() {
		conn = MySQLConnectionManager.getInstance();
	}
	
	@Override
	public Official[] getAllOfficials() {
		String sql = "SELECT * FROM gov_official;";
		Official[] ret = query(sql);
		return ret;
	}

	@Override
	public Official getOfficial(String official_id) {
		String sql = "SELECT * FROM gov_official WHERE official_id ='" + official_id + "';";
		Official[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	
	private Official[] query(String sql) {
		java.util.List<Official> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String official_id = resultSet.getString("official_id");
				String first_name = resultSet.getString("first_name");
				String last_name = resultSet.getString("last_name");
				String background = resultSet.getString("background");
				String position = resultSet.getString("position");
				
                Official gov = new Official();
                gov.setOfficialID(official_id);
                gov.setFirstName(first_name);
                gov.setLastName(last_name);
                gov.setBackground(background);
                gov.setPosition(position);
				ret.add(gov);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Official[ret.size()]);
	}	
}

