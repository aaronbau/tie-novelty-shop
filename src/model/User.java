package model;

public class User {
	private String username;
	private String email;
	private String password;
	private String salt;
	private String type;
	
	public User(String username, String email, String password) {
		this(username, email, password, "User", null);
	}
	
	public User(String username, String email, String password, String type) {
		this(username, email, password, type, null);
	}
	
	public User(String username, String email, String password, String type, String salt) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.type = type;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	

}
