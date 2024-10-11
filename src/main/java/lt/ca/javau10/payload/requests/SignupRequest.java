package lt.ca.javau10.payload.requests;

import java.util.Set;

public class SignupRequest {
	
	private String username;
	private String email;
	private Set<String> role;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public Set<String> getRole() {
		return role;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
