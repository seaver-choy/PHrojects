package phrojects.model.objects;

public class Comment {
	private String comment_id;
	private String username;
	private String title;
	private String description;

	public String getCommentID() {
		return comment_id;
	}
	
	public void setCommentID(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
