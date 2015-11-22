package phrojects.model.mysql_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.util.ArrayList;
import java.util.Arrays;

import phrojects.model.MySQLConnectionManager;
import phrojects.model.interface_dao.ProjectDAO;
import phrojects.model.objects.Account;
import phrojects.model.objects.Government;
import phrojects.model.objects.Project;
import phrojects.model.objects.Proposal;

public class MySQLProjectDAO implements ProjectDAO {
	private Connection conn;
	
	public MySQLProjectDAO() {
		conn = MySQLConnectionManager.getInstance();
	}
	
	@Override
	public Project[] getAllProjects() {
		String sql = "SELECT * FROM projects;";
		Project[] ret = query(sql);
		return ret;
	}
	
	@Override
	public void createProposedProject(Proposal p, Account a)
    {
        String sql = "INSERT INTO `projects` "
                + "(`title`, `description`, `department`, `regions`, `project_type`)"
                + " VALUES ('" 
                + p.getTitle() + "', '"
                + p.getDescription() + "', '"
                + p.getDepartment() + "', '"
                + p.getRegions() + "', '"
                + "PROPOSAL');";
        try (Statement stmt = conn.createStatement()) {
		stmt.execute(sql, RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        p.setProjectID(Integer.toString(rs.getInt(1)));
        
        sql = "INSERT INTO `proposed_projects` "
                + "(`project_id`, `status`, `priority`) "
                + "VALUES ('"
                + Integer.parseInt(p.getProjectID()) + "', '"
                + "PENDING_APPROVAL', '"
                + p.getPriority() + "');";
        
        stmt.execute(sql);
        
        sql = "INSERT INTO `account_has_projects` "
        		+ "(`username`, `project_id`) "
        		+ "VALUES ('"
        		+ a.getUsername() + "', '"
        		+ p.getProjectID() + "');";
        System.out.println(sql);
        stmt.execute(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
	@Override
    public void createGovernmentProject(Government g)
    {
        String sql = "INSERT INTO `projects` "
                + "(`title`, `description`, `department`, `regions`, `project_type`)"
                + " VALUES ('" 
                + g.getTitle() + "', '"
                + g.getDescription() + "', '"
                + g.getDepartment() + "', '"
                + g.getRegions() + "', '"
                + "GOVERNMENT');";
        try (Statement stmt = conn.createStatement()) {
		stmt.execute(sql, RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        g.setProjectID(Integer.toString(rs.getInt(1)));
        
        sql = "INSERT INTO `gov_projects` "
                + "(`project_id`, `project_cost`, `fund_source`, `sector`, "
                + "`icc_tb_date`, `icc_cc_date`, `neda_date`, `implement_period`, "
                + "`project_status`, `compliance_status`, `update_status`) "
                + "VALUES ('"
                + Integer.parseInt(g.getProjectID()) + "', '"
                + Double.parseDouble(g.getCost()) + "', '"
                + g.getSource() + "', '"
                + g.getSector() + "', '"
                + g.getTBDate() + "', '"
                + g.getCCDate() + "', '"
                + g.getNedaDate() + "', '"
                + g.getPeriod() + "', '"
                + g.getProjStatus() + "', '"
                + g.getCompStatus() + "', '"
                + g.getUpdate() + "');";
        
        stmt.execute(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	@Override
	public Project[] getBestProjects() {

		String sql = "SELECT P.project_id, P.title, P.description, P.department, p.regions, p.project_type, "
				+ "sum(case when V.status = 'UPVOTE' then 1 else -1 end) as VOTE_VALUE "
		        + "from account_has_votes V, projects P "
		        + "WHERE V.project_id = P.project_id "
		        + "GROUP BY project_id ORDER BY VOTE_VALUE DESC;";

		Project[] ret = query(sql);
		return ret;
	}


	@Override
	public Project getProject(String project_id) {
		String sql = "SELECT * FROM projects WHERE project_id ='" + project_id + "';";
		Project[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}

	@Override
	public Project[] getAccountProjects(String username) {
		String sql = "SELECT P.project_id, P.title, P.description, P.department, P.regions, P.project_type " 
		              + "FROM projects P, account_has_projects A "
		              + "WHERE A.username = '" + username + "' "
		              + "AND  A.project_id = P.project_id";
		Project[] ret = query(sql);
		return ret;
	}

	@Override
	public Project getAccountProject(String project_id, String username) {
		String sql = "SELECT P.project_id, P.title, P.description, P.department, P.regions, P.project_type "
		              + "FROM projects P, account_has_projects A "
		              + "WHERE P.project_id = '" + project_id + "' "
		              + "AND P.project_id = A.project_id "
		              + "AND A.username = '" + username + "';";
		Project[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}

	@Override
	public Project[] getOfficialProjects(String official_id) {
		String sql = "SELECT P.project_id, P.title, P.description, P.department, P.regions, P.project_type "
		              + "FROM projects P, official_has_projects O "
		              + "WHERE O.official_id = '" + official_id + "' "
		              + "AND  O.project_id = P.project_id";
		Project[] ret = query(sql);
		return ret;
	}

	@Override
	public Project getOfficialProject(String project_id, String official_id) {
		String sql = "SELECT P.project_id, P.title, P.description, P.department, P.regions, P.project_type "
		              + "FROM projects P, official_has_projects O "
		              + "WHERE P.project_id = '" + project_id + "' "
		              + "AND P.project_id = O.project_id "
		              + "AND O.official_id = '" + official_id + "';";
		Project[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}

	@Override
	public int getUpvotes(String project_id){
            try{
		String sql = "SELECT COUNT(*) "
					  + "FROM account_has_votes V "
					  + "WHERE V.project_id ='" + project_id + "' "
					  + "AND V.status = 'UPVOTE';";
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
            if(!rs.wasNull()){
            rs.next();
            return rs.getInt(1);
            }
		}
        }catch(Exception e){
            System.out.print(e);
        }
            return 0;
	}

	@Override
	public int getDownvotes(String project_id) {
		String sql = "SELECT COUNT(*) "
					  + "FROM account_has_votes V "
					  + "WHERE V.project_id ='" + project_id + "' "
					  + "AND V.status = 'DOWNVOTE';";

	try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
            if(!rs.wasNull()){
            rs.next();
            return rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
	}

	private String[] queryProposal(String sql) {
		java.util.List<String> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String status = resultSet.getString("status");
			    String priority = resultSet.getString("priority");
				
                String[] details = new String[2];
                details[0] = status;
                details[1] = priority;
                   
                ret.addAll(Arrays.asList(details));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new String[ret.size()]);
	}

	private String[] getProposals(String project_id) {
		String sql = "SELECT * FROM proposed_projects WHERE project_id ='" + project_id + "';";
		String[] ret = queryProposal(sql);
		return ret;
	}

	private String[] queryGovernment(String sql) {
		java.util.List<String> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String project_cost = resultSet.getString("project_cost");
				String fund_source = resultSet.getString("fund_source");
				String sector = resultSet.getString("sector");
				String icc_tb_date = resultSet.getString("icc_tb_date");
				String icc_cc_date = resultSet.getString("icc_cc_date");
				String neda_date = resultSet.getString("neda_date");
				String implement_period	= resultSet.getString("implement_period");
				String project_status = resultSet.getString("project_status");
				String compliance_status = resultSet.getString("compliance_status");
				String update_status = resultSet.getString("update_status");
			
                String[] details = new String[10];
                details[0] = project_cost;
                details[1] = fund_source;
                details[2] = sector;
                details[3] = icc_tb_date;
                details[4] = icc_cc_date;
                details[5] = neda_date;
                details[6] = implement_period;
                details[7] = project_status;
                details[8] = compliance_status;
                details[9] = update_status;
				ret.addAll(Arrays.asList(details));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new String[ret.size()]);
	}

	private String[] getGovernment(String project_id) {
		String sql = "SELECT * FROM gov_projects WHERE project_id ='" + project_id + "';";
		String[] ret = queryGovernment(sql);
		return ret;
	}
	
	private Project[] query(String sql) {
		java.util.List<Project> ret = new ArrayList<>();
		if(conn == null)
		{
			System.out.println("alsjdajsdlkjasldj");
		}
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String project_id = resultSet.getString("project_id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				String department = resultSet.getString("department");
				String regions = resultSet.getString("regions");
				String project_type = resultSet.getString("project_type");
				System.out.println(project_type);
				if(project_type.equals("PROPOSAL")){
					Proposal proj = new Proposal();

					String[] details = new String[2];
					details = getProposals(project_id);
                	
	                proj.setProjectID(project_id);
	                proj.setTitle(title);
	                proj.setDescription(description);
	                proj.setDepartment(department);
	                proj.setRegions(regions);
	                proj.setProjectType(project_type);

	                proj.setStatus(details[0]);
	                proj.setPriority(details[1]);
	                ret.add(proj);
	            }

	            else if(project_type.equals("GOVERNMENT")){
					Government proj = new Government();

					String[] details = new String[10];
					details = getGovernment(project_id);
					System.out.println(details.length);
					proj.setProjectID(project_id);
	                proj.setTitle(title);
	                proj.setDescription(description);
	                proj.setDepartment(department);
	                proj.setRegions(regions);
	                proj.setProjectType(project_type);

	                proj.setCost(details[0]);
	                proj.setSource(details[1]);
	                proj.setSector(details[2]);
	                proj.setTBDate(details[3]);
	                proj.setCCDate(details[4]);
	                proj.setNedaDate(details[5]);
	                proj.setPeriod(details[6]);
	                proj.setProjStatus(details[7]);
	                proj.setCompStatus(details[8]);
	                proj.setUpdate(details[9]);
	                ret.add(proj);
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Project[ret.size()]);
	}	
}

