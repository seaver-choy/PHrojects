package phrojects.model.interface_dao;

import java.sql.SQLException;

import phrojects.model.objects.Comment;

public interface CommentDAO {
	public Comment[] getAllComments(String project_id);
	public Comment getComment(String project_id, String comment_id);
}   