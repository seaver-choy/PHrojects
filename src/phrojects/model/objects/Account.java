package phrojects.model.objects;

public class Account {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private String mobile_num;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
    }

    public String getMobile() {
		return mobile_num;
	}
	
	public void setMobile(String mobile_num) {
		this.mobile_num = mobile_num;
    }
}
