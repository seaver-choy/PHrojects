package phrojects.model.mysql_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import phrojects.model.MySQLConnectionManager;
import phrojects.model.interface_dao.AccountDAO;
import phrojects.model.objects.Account;

public class MySQLAccountDAO implements AccountDAO {
	private Connection conn;
	
	public MySQLAccountDAO() {
		conn = MySQLConnectionManager.getInstance();
	}
	
	@Override
	public Account[] getAllAccounts() {
		String sql = "SELECT * FROM accounts;";
		Account[] ret = query(sql);
		return ret;
	}

	
	@Override
	public Account getProjectAccount(String project_id) {
		String sql = "SELECT A.username, A.password, A.first_name, A.last_name, A.email, A.mobile_num "
					 + "FROM accounts A, account_has_projects P "
					 + "WHERE P.project_id ='" + project_id + "' "
					 + "AND P.username = A.username;";
		Account[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	
	@Override
	public Account getAccount(String username) {
		String sql = "SELECT * FROM accounts WHERE username ='" + username + "';";
		Account[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	
	private Account[] query(String sql) {
		java.util.List<Account> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String first_name = resultSet.getString("first_name");
				String last_name = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String mobile_num = resultSet.getString("mobile_num");
				
                Account acct = new Account();
                acct.setUsername(username);
				acct.setPassword(password);
                acct.setFirstName(first_name);
                acct.setLastName(last_name);
                acct.setEmail(email);
                acct.setMobile(mobile_num);
				ret.add(acct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Account[ret.size()]);
	}
	
	@Override
	public void createUserAccount(Account user) throws SQLException {
		String sql = "INSERT INTO accounts(username, password, "
				+ "first_name, last_name, email, mobile_num) VALUES('"
				+ user.getUsername() +"', '"
				+ user.getPassword() + "', '"
				+ user.getFirstName() + "', '"
				+ user.getLastName() + "', '"
				+ user.getEmail() + "', '"
				+ user.getMobile() + "');";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
	
	@Override
	public void updateUserAccount(Account user) {
		String sql = "UPDATE accounts SET password ='" + user.getPassword()
				+ "', first_name = '" + user.getFirstName()
				+ "', last_name ='" + user.getLastName()
				+ "', email = '" + user.getEmail()
				+ "', mobile_num = '" + user.getMobile()
				+ "' WHERE username = '" + user.getUsername() +"';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAccount(Account acct) {
		String sql = "DELETE FROM accounts WHERE "
				+ "username = '" + acct.getUsername() + "';";
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

