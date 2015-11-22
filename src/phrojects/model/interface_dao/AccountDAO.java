package phrojects.model.interface_dao;

import java.sql.SQLException;

import phrojects.model.objects.Account;

public interface AccountDAO {
	public Account[] getAllAccounts();
	public Account getAccount(String username);
	public void createUserAccount(Account acct) throws SQLException;
    public void updateUserAccount(Account user);
	public void deleteAccount(Account acct);
	public Account getProjectAccount(String project_id);
}   