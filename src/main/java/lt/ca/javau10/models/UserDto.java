package lt.ca.javau10.models;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lt.ca.javau10.entities.Grade;

@Component
public class UserDto implements UserDetails {

private static final long serialVersionUID = -1L;
	
	private Long id;
	private String username;
	private String email;
	private List<Grade> grades;
	private Set<Role> roles;
	@JsonIgnore
	private String password;
	
public UserDto() {}
	
	public UserDto(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public UserDto(String username, String email, List<Grade> grades, Set<Role> roles, String password) {
		this.username = username;
		this.email = email;
		this.grades = grades;
		this.roles = roles;
		this.password = password;
	}

	public UserDto(Long id, String username, String email, List<Grade> grades, Set<Role> roles, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.grades = grades;
		this.roles = roles;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toSet());
	}

}
