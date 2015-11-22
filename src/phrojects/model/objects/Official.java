package phrojects.model.objects;

public class Official {
	private String official_id;
	private String last_name;
	private String first_name;
	private String background;
	private String position;
	
	public String getOfficialID() {
		return official_id;
	}
	
	public void setOfficialID(String official_id) {
		this.official_id = official_id;
	}

	public String getFirstName() {
		return first_name;
	}
	
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
    }

    public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
    }
}
