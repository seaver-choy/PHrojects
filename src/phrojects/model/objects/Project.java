package phrojects.model.objects;

public class Project{
	private String project_id;
	private String title;
	private String description;
	private String department;
	private String regions;
	private String project_type;

	public String getProjectID() {
		return project_id;
	}
	
	public void setProjectID(String project_id) {
		this.project_id = project_id;
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

	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRegions() {
		return regions;
	}
	
	public void setRegions(String regions) {
		this.regions = regions;
	}

	public String getProjectType() {
		return project_type;
	}
	
	public void setProjectType(String project_type) {
		this.project_type = project_type;
	}
}