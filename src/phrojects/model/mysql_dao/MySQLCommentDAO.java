package phrojects.model.mysql_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import phrojects.model.MySQLConnectionManager;

import phrojects.model.interface_dao.CommentDAO;
import phrojects.model.objects.Comment;

public class MySQLCommentDAO implements CommentDAO {
	private Connection conn;
	
	public MySQLCommentDAO() {
		conn = MySQLConnectionManager.getInstance();
	}
	
	@Override
	public Comment[] getAllComments(String project_id) {
		String sql = "SELECT * FROM comments WHERE project_id ='" + project_id + "';";
		Comment[] ret = query(sql);
		return ret;
	}

	@Override
	public Comment getComment(String project_id, String comment_id) {
		String sql = "SELECT * FROM comments WHERE project_id ='" + project_id + "' AND comment_id = '" + comment_id + "';";
		Comment[] ret = query(sql);
		return (ret.length == 0) ? null : ret[0];
	}
	
	private Comment[] query(String sql) {
		java.util.List<Comment> ret = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				String comment_id = resultSet.getString("comment_id");
				String username = resultSet.getString("username");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				
                Comment com = new Comment();
                com.setCommentID(comment_id);
                com.setUsername(username);
                com.setTitle(title);
                com.setDescription(description);
				ret.add(com);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toArray(new Comment[ret.size()]);
	}	
}

