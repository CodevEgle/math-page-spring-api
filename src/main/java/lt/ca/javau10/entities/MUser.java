package lt.ca.javau10.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lt.ca.javau10.models.Role;

@Entity
@Table (name="Vartotojai")
public class MUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    
	@Column(nullable = false, unique = false)
    private String email;
    
	@Column(nullable = false, unique = true)
    private String password;
    
    @OneToMany(mappedBy = "muser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Grade> grades = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(  name = "user_roles", 
    			joinColumns = @JoinColumn(name = "user_id"), 
    			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
    
    public MUser() {}
    
	public MUser(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

    
}
