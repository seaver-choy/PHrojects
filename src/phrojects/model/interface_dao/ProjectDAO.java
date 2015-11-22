package phrojects.model.interface_dao;

import java.sql.SQLException;

import phrojects.model.objects.Government;
import phrojects.model.objects.Project;
import phrojects.model.objects.Proposal;

public interface ProjectDAO {
	public Project[] getAllProjects();
	public Project getProject(String project_id);
	public Project[] getAccountProjects(String username);
	public Project getAccountProject(String username, String project_id);
	public Project[] getOfficialProjects(String official_id);
	public Project getOfficialProject(String username, String project_id);
	public int getUpvotes(String project_id);
	public int getDownvotes(String project_id);
	//public void deleteProject(String project_id);
	Project[] getBestProjects();
	void createGovernmentProject(Government g);
	void createProposedProject(Proposal p);
}   